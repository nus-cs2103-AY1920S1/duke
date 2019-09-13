package seedu.duke.core;

import seedu.duke.command.ByeCommandCli;
import seedu.duke.command.Command;
import seedu.duke.command.UnknownCommand;
import seedu.duke.parser.Parser;
import seedu.duke.statistic.Statistic;
import seedu.duke.storage.Storage;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;


/**
 * Project Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 * storage attribute is a Storage object, which helps read and write data to the text file
 *
 */
public class Duke {

    private Storage taskStorage;
    private Storage statStorage;
    private TaskList tasks;
    private Statistic stats;
    private CommandLineUi cli;
    private GraphicalUi gui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/117.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/commando.jpg"));

    private String taskFilePath = "C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\tasks.txt";
    private String statFilePath = "C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\stats.txt";

    /**
     * Default constructor to support seedu.duke.core.Launcher of javaFX.
     */
    public Duke() {
        taskStorage = new Storage(taskFilePath);
        statStorage = new Storage(statFilePath);

        gui = new GraphicalUi();
        cli = new CommandLineUi();

    }

    /**
     * Instantiates a Duke object with filePath.
     *
     * @param filePath absolute filepath of the where the text file is stored.
     *                 Eg "C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\tasks.txt".
     */
    public Duke(String filePath) {
        gui = new GraphicalUi();
        cli = new CommandLineUi();
        taskStorage = new Storage(filePath);
        statStorage = new Storage(this.statFilePath);
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                // new DialogBox(userText, new ImageView(user)),
                // new DialogBox(dukeText, new ImageView(duke))
                DialogBox.getUserDialog(input, user),
                DialogBox.getDukeDialog(response, duke)
        );
        userInput.clear();
    }

    /**
     * Returns the response for the user input.
     * Represents the GUI of Duke.
     *
     * @param input Command input string.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        String command = Parser.parseCommand(input);

        try {
            // Loads the data from txt file to the TaskList object, tasks.
            tasks = new TaskList(this.taskStorage.loadTasks());

            // Loads the stats from txt file to the Statistic object, stats
            stats = new Statistic(this.statStorage.loadStats());

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (DukeException e){
            System.out.println(e.getMessage());
        }

        String output = "";
        try {
            output = executeTasksGui(input);

            // Saves tasks to txt file before returning output to GUI.
            this.taskStorage.clearTaskFileBeforeSaving();
            for (int i = 0; i < this.tasks.getSize(); i++) {
                this.taskStorage.writeToTaskFile(this.tasks.getTask(i).toSaveString());
            }

            // Saves stats file before returning output to GUI.
            statStorage.saveStatFile(stats);

            return output;
        } catch (DukeException e) {
            DukeErrorInterface dei = (e1) -> e1.getMessage();
            return dei.getLoadingError(e);
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Executes the Duke Command Line Interface.
     */
    public void run() {
        Boolean isCommandLineInterface = true;
        System.out.println(cli.getWelcomeString());
        // Boolean isBye = false;
        try {
            // Loads the data from txt file to the TaskList object, tasks.
            tasks = new TaskList(this.taskStorage.loadTasks());

            // Loads the stats data from txt file.
            stats = new Statistic(this.statStorage.loadStats());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (DukeException e){
            System.out.println(e.getMessage());
        }
        // Creates scanner object to handle input.
        Scanner in = new Scanner(System.in);
        //String fullCommand = in.nextLine().trim();
        String fullCommand = "";
        Command command = new UnknownCommand();


        while ( command instanceof ByeCommandCli == false) {
            String outputString = "";
            fullCommand = in.nextLine().trim();
            try {
                command = Parser.getCommand(fullCommand, cli);
                outputString = command.execute(fullCommand, cli, tasks, taskStorage, stats, statStorage);
            } catch (IOException e) {
                outputString = e.getMessage();
            } catch (DukeException e) {
                outputString = e.getMessage();
            }
            System.out.println(outputString);
            //fullCommand = in.nextLine().trim();
        }

        /*
        while (!isBye) {
            try {
                isBye = executeTasksCli(fullCommand);
            } catch (DukeException e) {
                DukeErrorInterface dei = (e1) -> e1.getMessage();
                System.out.println(dei.getLoadingError(e));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getCause());
            }
            if (isBye) {
                break;
            }
            fullCommand = in.nextLine().trim();
        }

         */
    }

    /**
     * Executes the Command Line Interface for Duke.
     *
     * @param fullCommand User input string.
     * @return Boolean isBye. If command is "bye", will return true and exit the while loop.
     * @throws DukeException To catch some invalid commands.
     * @throws IOException To catch file error when interacting with Storage class.
     */
    public Boolean executeTasksCli(String fullCommand) throws DukeException, IOException {

        /*
        // parseCommand will return the first word of fullCommand
        String taskType = Parser.parseCommand(fullCommand);

        switch(taskType) {

        case ("list"):
            System.out.println(listRoutine(cli, tasks));
            break;
        case ("done"):
            System.out.println(doneRoutine(cli, tasks, fullCommand));
            break;
        case("todo"):
            System.out.println(todoRoutine(cli, tasks, fullCommand));
            break;
        case("deadline"):
            System.out.println(deadlineRoutine(cli, tasks, fullCommand));
            break;
        case("event"):
            System.out.println(eventRoutine(cli, tasks, fullCommand));
            break;
        case("delete"):
            System.out.println(deleteRoutine(cli, tasks, fullCommand));
            break;
        case("find"):
            System.out.println(findRoutine(cli, tasks, fullCommand));
            break;
        case("bye"):
            System.out.println(byeRoutineCli(cli, tasks, taskStorage, stats, statStorage));
            return true;
        case("stats"):
            String statCommandType = Parser.parseStatCommand(fullCommand);
            switch(statCommandType){
            case ("all"):
                System.out.println(getAllStatsRoutine(cli, stats));
                break;
            case ("reset"):
                System.out.println(resetStatsRoutine(cli, stats));
                break;
            case ("event"):
                System.out.println(completedEventStatsRoutine(cli, stats, tasks));
                break;
            default:
                unknownCommandRoutine();
            }
            break;

        default:
            unknownCommandRoutine();
            return false;
        }
        return false;

         */
        return true;
    }

    /**
     * Executes the Graphical User Interface logic for Duke.
     *
     * @param fullCommand Input string obtained from GUI.
     * @return Output string from Ui class.
     * @throws DukeException Handles case when command is recognized eg"list" but following description is unrecognized.
     * @throws IOException Storage class txt file handling.
     */
    public String executeTasksGui(String fullCommand) throws DukeException, IOException {
        String taskType = Parser.parseCommand(fullCommand);

        switch (taskType){
        case ("list"):
            return listRoutine(gui, tasks);
        case ("done"):
            return doneRoutine(gui, tasks, fullCommand);
        case("todo"):
            return todoRoutine(gui, tasks, fullCommand);
        case("deadline"):
            return deadlineRoutine(gui, tasks, fullCommand);
        case("event"):
            return eventRoutine(gui, tasks, fullCommand);
        case("delete"):
            return deleteRoutine(gui, tasks, fullCommand);
        case("find"):
            return findRoutine(gui, tasks, fullCommand);
        case("bye"):
            return byeRoutineGui(gui, tasks);
        default:
            unknownCommandRoutine();
            return null;
        }
    }

    /**
     * Handles the case when the user command is unrecognized.
     *
     * @throws DukeException Error class.
     */
    public void unknownCommandRoutine() throws DukeException {
        throw new DukeException(":-( OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public String completedEventStatsRoutine(Ui ui, Statistic stats, TaskList tasks) {
        stats.incrementTotalCommandsExecuted();
        return ui.getCompletedEventStatSequence(stats,tasks );
    }

    /**
     * Executes and returns the String for the LIST command.
     *
     * @param ui Ui object.
     * @param tasks Tasklist object.
     * @return String that needs to be printed to the user after LIST command.
     */
    public String listRoutine(Ui ui, TaskList tasks) {
        stats.incrementTotalCommandsExecuted();
        return ui.getListSequence(tasks);
    }

    /**
     * Executes and returns the String for BYE command for CLI.
     * For CLI, txt file is saved during BYE command.
     *
     *
     */
    public String byeRoutineCli(Ui ui, TaskList tasks, Storage taskStorage, Statistic stat, Storage statStorage) throws IOException {
        stats.incrementTotalCommandsExecuted();
        // Clear the tasks txt file and adds headers.
        taskStorage.clearTaskFileBeforeSaving();

        // Saves the task list to the file, following the pre-defined format.
        for (int i = 0; i < tasks.getSize(); i++) {
            taskStorage.writeToTaskFile(tasks.getTask(i).toSaveString());
        }

        // Saves the stats data to the stats file.
        statStorage.saveStatFile(stat);

        return ui.getByeSequence();
    }

    /**
     * Executes and returns the String for BYE command for GUI.
     * For GUI, txt file is saved after every command.
     *
     * @param ui Ui object.
     * @param tasks TaskList object.
     * @return Bye string sequence
     */
    public String byeRoutineGui(Ui ui, TaskList tasks) {
        stats.incrementTotalCommandsExecuted();
        // For GUI, txt file is always saved after each change
        return (ui.getByeSequence());
    }

    /**
     * Executes and returns the String for FIND command.
     *
     * @param ui Ui object.
     * @param tasks TaskList object.
     * @param fullCommand User command String.
     * @return Find string sequence.
     */
    public String findRoutine(Ui ui, TaskList tasks, String fullCommand) {
        stats.incrementTotalCommandsExecuted();
        // Parser will parse the command and obtain the searchString.
        // findSimilarTasks will return a TaskList containing only matching tasks.
        TaskList similarTasks = tasks.findSimilarTasks(Parser.getFindTask(fullCommand));
        return ui.getFoundTasks(similarTasks);
    }

    /**
     * Executes and returns the String for DELETE command.
     *
     * @param ui Ui object.
     * @param tasks TaskList object.
     * @param fullCommand User command String.
     * @return Delete string sequence.
     * @throws DukeException Duke exception to handle invalid task index.
     */
    public String deleteRoutine(Ui ui, TaskList tasks, String fullCommand) throws DukeException {
        stats.incrementTotalCommandsExecuted();
        int taskNum = Parser.getDeletedTaskNum(fullCommand);

        // taskList index (starts from 0) differs from taskNum (starts from 1) by 1.
        taskNum--;

        if (taskNum >= tasks.getSize()) {
            // taskNum does not exist.
            throw new DukeException("Task no. " + (taskNum + 1) + " does not exist");
        }

        Task taskToDelete = tasks.getTask(taskNum);
        tasks.deleteTask(taskNum);

        stats.incrementTotalTasksDeleted();

        return ui.getDeleteSequence(tasks, taskToDelete);
    }

    /**
     * Executes and returns the String EVENT command.
     *
     * @param ui Ui object.
     * @param tasks TaskList object.
     * @param fullCommand User command string.
     * @return Event String sequence.
     * @throws DukeException Handles the error case when there is no description or location.
     */
    public String eventRoutine(Ui ui, TaskList tasks, String fullCommand) throws DukeException {
        stats.incrementTotalCommandsExecuted();
        if ((fullCommand.length() < 6)) {
            // Input is only "event".

            // fullCommand contains only the string "event".
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");

        } else if ((fullCommand.lastIndexOf('/') < 1)
                || (4 + fullCommand.lastIndexOf('/') > fullCommand.length()))  {
            // fullCommand does not contain '/' char or there are no chars after "/at".
            throw new DukeException(":-( OOPS!!! The location of an event cannot be empty.");
        }
        String description = Parser.getEventDescription(fullCommand);
        String extraDescription = Parser.getEventLocation(fullCommand);

        Event newEvent = new Event(description, extraDescription);

        tasks.addTask(newEvent);

        return ui.getEventSequence(tasks, newEvent);
    }

    /**
     * Executes and returns the String DEADLINE command.
     *
     * @param ui Ui object.
     * @param tasks TaskList object.
     * @param fullCommand User command string.
     * @return Deadline string sequence
     * @throws DukeException To handle the case when description or time period is empty.
     */
    public String deadlineRoutine(Ui ui, TaskList tasks, String fullCommand) throws DukeException {
        stats.incrementTotalCommandsExecuted();
        if ((fullCommand.length() < 9)) {

            // fullCommand contains only the string "deadline".
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");

        } else if ((fullCommand.lastIndexOf('/') < 1)
                || (4 + fullCommand.lastIndexOf('/') > fullCommand.length()))  {

            // fullCommand does not contain '/' chars or there are no char after "/by".
            throw new DukeException("☹ OOPS!!! The time period of an event cannot be empty.");

        }

        String description = Parser.getDeadlineDescription(fullCommand);
        String extraDescription = Parser.getDeadlineDateTime(fullCommand);

        assert description != null : "Description should not be null";
        assert extraDescription != null : "Extra Description should not be null";

        Deadline newDeadline = new Deadline(description, extraDescription);

        tasks.addTask(newDeadline);

        return ui.getDeadlineSequence(tasks, newDeadline);
    }

    /**
     * Executes and returns the String TODO command.
     *
     * @param ui Ui object.
     * @param tasks TaskList object.
     * @param fullCommand User command string.
     * @return Todo string sequence.
     * @throws DukeException Handles the case when description is empty.
     */
    public String todoRoutine(Ui ui, TaskList tasks, String fullCommand) throws DukeException {
        stats.incrementTotalCommandsExecuted();
        if (fullCommand.length() < 5) {
            // fullCommand contains only the string "todo".
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        String description = Parser.getTodoDescription(fullCommand);

        Todo newTodo = new Todo(description);

        tasks.addTask(newTodo);

        return ui.getTodoSequence(tasks, newTodo);
    }

    /**
     * Executes and returns the String for DONE command.
     *
     * @param ui Ui object.
     * @param tasks TaskList object.
     * @param fullCommand User command string.
     * @return Done string sequence.
     */
    public String doneRoutine(Ui ui, TaskList tasks, String fullCommand) {
        stats.incrementTotalCommandsExecuted();

        int taskNum = Parser.getFinishedTaskNum(fullCommand);

        // taskList index (starts from 0) differs from taskNum (starts from 1) by 1,
        taskNum--;

        tasks.getTask(taskNum).setDone(stats);

        return ui.getDoneSequence(tasks, taskNum);
    }

    /**
     * Main method to run Duke.
     *
     * @param args Main entry point.
     */
    public static void main(String args[]) {
        new Duke("C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\tasks.txt").run();
    }

    public String resetStatsRoutine(Ui ui, Statistic stats) {
        stats.resetStats();
        return ui.getResetStatSequence(stats);
    }

    public String getAllStatsRoutine(Ui ui, Statistic stats) {
        stats.incrementTotalCommandsExecuted();
        return ui.getAllStatSequence(stats);
    }

    /**
     * possibleTasks  is an enumeration of the constant, pre-defined, recognizable commands.
     */
    enum PossibleTasks {
        BYE,
        LIST,
        DONE,
        DELETE,
        EVENT,
        TODO,
        DEADLINE,
        FIND
    }
}



