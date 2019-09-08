package seedu.duke;

import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

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

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/117.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/commando.jpg"));

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
     * Instantiates a Duke object.
     *
     * @param filePath absolute filepath of the where the text file is stored.
     *                 Eg "C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\tasks.txt".
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {

        // return "Duke heard: " + input;

        String command = Parser.parseCommand(input);

        return command;

    }

    /**
     * Executes the Duke Command Line Interface.
     */
    public void run() {
        // Variable initialization. description will hold the description of all tasks.
        // extraDescription will hold either the
        // dateTime attribute of Deadline class or location attribute of Event class.
        String output = "";
        String taskType = "";
        String description = "";
        String extraDescription = "";
        int taskNum = -1;

        ui.showWelcome();

        try {

            // Loads the data from txt file to the TaskList object, tasks.
            tasks = new TaskList(this.storage.load());

        } catch (FileNotFoundException e) {

            System.out.println(e.getMessage());

        }

        // Creates scanner object to handle input.
        Scanner in = new Scanner(System.in);
        String fullCommand = in.nextLine().trim();

        while (true) {

            taskType = Parser.parseCommand(fullCommand);

            try {

                if (taskType.equals(PossibleTasks.LIST.toString().toLowerCase())) {
                    // LIST command.
                    listRoutineCLI(ui, tasks);

                } else if (taskType.equals(PossibleTasks.DONE.toString().toLowerCase())) {
                    // DONE command.

                    doneRoutineCLI(ui, tasks, fullCommand);

                } else if (taskType.equals(PossibleTasks.TODO.toString().toLowerCase())) {
                    // TODO command

                    todoRoutineCLI(ui, tasks, fullCommand);;

                } else if (taskType.equals(PossibleTasks.DEADLINE.toString().toLowerCase())) {
                    // DEADLINE command.

                    deadlineRoutineCLI(ui, tasks, fullCommand);

                } else if (taskType.equals(PossibleTasks.EVENT.toString().toLowerCase())) {
                    // EVENT command.

                    eventRoutineCLI(ui, tasks, fullCommand);

                } else if (taskType.equals(PossibleTasks.DELETE.toString().toLowerCase())) {
                    // DELETE command.

                    deleteRoutineCLI(ui, tasks, fullCommand);

                } else if (taskType.equals(PossibleTasks.FIND.toString().toLowerCase())) {
                    // FIND command
                    findRoutineCLI(ui, tasks, fullCommand);

                } else if (taskType.equals(PossibleTasks.BYE.toString().toLowerCase())) {
                    // BYE command

                    byeRoutineCLI(ui, tasks, storage);
                    break;
                    // Exits while loop once "bye" is entered.

                } else {
                    // An unrecognizable command is detected.
                    unknownCommandRoutine();

                }
            } catch (DukeException e) {

                ui.showLoadingError(e);

            } catch (StringIndexOutOfBoundsException e) {

                System.out.println(e.getMessage());

            } catch (NumberFormatException e) {

                System.out.println(e.getMessage());

            } catch (IOException e) {

                System.out.println(e.getCause());

            }

            fullCommand = in.nextLine().trim();
            output = "";
        }

    }

    public void unknownCommandRoutine() throws DukeException{
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void listRoutineCLI(Ui ui, TaskList tasks){
        ui.printList(tasks);
    }

    public String listRoutineGUI(Ui ui, TaskList tasks){
        listRoutineCLI(ui, tasks);
        return ui.getPrintList(tasks);
    }

    public void byeRoutineCLI(Ui ui, TaskList tasks, Storage storage) throws IOException{
        ui.printByeSequence();

        storage.clearFileBeforeSaving();
        // Clear the txt file and adds headers.

        // Saves the task list to the file, following the pre-defined format.
        for (int i = 0; i < tasks.getSize(); i++) {
            storage.writeToFile(tasks.getTask(i).toSaveString());
        }
    }

    public String byeRoutineGUI(Ui ui, TaskList tasks, Storage storage) throws IOException{
        byeRoutineCLI(ui,tasks,storage);
        return (ui.getByeSequence());
    }

    public void findRoutineCLI(Ui ui, TaskList tasks, String fullCommand){
        // Parser will parse the command and obtain the searchString.
        // findSimilarTasks will return a TaskList containing only matching tasks.
        TaskList similarTasks = tasks.findSimilarTasks(Parser.getFindTask(fullCommand));
        ui.printFoundTasks(similarTasks);
    }

    public String findRoutineGUI(Ui ui, TaskList tasks, String fullCommand){
        findRoutineCLI(ui, tasks,fullCommand);
        return (ui.getFoundTasks(tasks));
    }

    public void deleteRoutineCLI(Ui ui, TaskList tasks, String fullCommand) throws DukeException{
        int taskNum = Parser.getDeletedTaskNum(fullCommand);

        // taskList index (starts from 0) differs from taskNum (starts from 1) by 1.
        taskNum--;

        if (taskNum >= tasks.getSize()) {
            // taskNum does not exist.
            throw new DukeException("Task no. " + (taskNum + 1) + " does not exist");
        }

        Task taskToDelete = tasks.getTask(taskNum);
        tasks.deleteTask(taskNum);
        ui.printDeleteSequence(tasks, taskToDelete);
        taskToDelete = null;
    }

    public String deleteRoutineGUI(Ui ui, TaskList tasks, String fullCommand) throws DukeException{
        int taskNum = Parser.getDeletedTaskNum(fullCommand);
        taskNum--;
        if (taskNum >= tasks.getSize()) {
            throw new DukeException("Task no. " + (taskNum + 1) + " does not exist");
        }
        Task taskToDelete = tasks.getTask(taskNum);
        tasks.deleteTask(taskNum);
        taskToDelete = null;
        return (ui.getDeleteSequence(tasks,taskToDelete ));
    }

    public void eventRoutineCLI(Ui ui, TaskList tasks, String fullCommand) throws DukeException{
        if ((fullCommand.length() < 6)) {
            // Input is only "event".

            // fullCommand contains only the string "event".
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");

        } else if ((fullCommand.lastIndexOf('/') < 1)
                || (4 + fullCommand.lastIndexOf('/') > fullCommand.length()))  {

            // fullCommand does not contain '/' char or there are no chars after "/at".
            throw new DukeException("☹ OOPS!!! The time period of an event cannot be empty.");

        }

        String description = Parser.getEventDescription(fullCommand);
        String extraDescription = Parser.getEventLocation(fullCommand);

        Event newEvent = new Event(description, extraDescription);

        tasks.addTask(newEvent);

        ui.printEventSequence(tasks, newEvent);
    }

    public String eventRoutineGUI(Ui ui, TaskList tasks, String fullCommand) throws DukeException{
        eventRoutineCLI(ui, tasks, fullCommand);
        String description = Parser.getEventDescription(fullCommand);
        String extraDescription = Parser.getEventLocation(fullCommand);
        Event newEvent = new Event(description, extraDescription);
        tasks.addTask(newEvent);
        return (ui.getEventSequence(tasks, newEvent));
    }

    public void deadlineRoutineCLI(Ui ui, TaskList tasks, String fullCommand) throws DukeException{
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

        Deadline newDeadline = new Deadline(description, extraDescription);

        tasks.addTask(newDeadline);

        ui.printDeadlineSequence(tasks, newDeadline);
    }

    public String deadlineRoutineGUI(Ui ui, TaskList tasks, String fullCommand) throws DukeException{
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

        Deadline newDeadline = new Deadline(description, extraDescription);

        tasks.addTask(newDeadline);

        return (ui.getDeadlineSequence(tasks, newDeadline));
    }

    public void todoRoutineCLI(Ui ui, TaskList tasks, String fullCommand) throws DukeException{
        if (fullCommand.length() < 5) {
            // fullCommand contains only the string "todo".
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        String description = Parser.getTodoDescription(fullCommand);

        Todo newTodo = new Todo(description);

        tasks.addTask(newTodo);

        ui.printTodoSequence(tasks, newTodo);
    }

    public String todoRoutineGUI(Ui ui, TaskList tasks, String fullCommand) throws DukeException{
        if (fullCommand.length() < 5) {
            // fullCommand contains only the string "todo".
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        String description = Parser.getTodoDescription(fullCommand);

        Todo newTodo = new Todo(description);

        tasks.addTask(newTodo);

        return (ui.getTodoSequence(tasks, newTodo));
    }

    public void doneRoutineCLI(Ui ui, TaskList tasks, String fullCommand){
        int taskNum = Parser.getFinishedTaskNum(fullCommand);

        // taskList index (starts from 0) differs from taskNum (starts from 1) by 1,
        taskNum--;

        tasks.getTask(taskNum).setDone();

        ui.printDoneSequence(tasks, taskNum);
    }

    public String doneRoutineGUI(Ui ui, TaskList tasks, String fullCommand){
        int taskNum = Parser.getFinishedTaskNum(fullCommand);

        // taskList index (starts from 0) differs from taskNum (starts from 1) by 1,
        taskNum--;

        tasks.getTask(taskNum).setDone();

        return (ui.getDoneSequence(tasks, taskNum));
    }

    /**
     * Main method to run Duke.
     *
     * @param args Main entry point.
     */
    public static void main (String args[]) {
        new Duke("C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\tasks.txt").run();
    }


    /**
     * possibleTasks is an enumeration of the constant, pre-defined, recognizable commands.
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

    /**
     * Default constructor to support seedu.duke.Launcher of javaFX.
     */
    public Duke(){

    }

}



