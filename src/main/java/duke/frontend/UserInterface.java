package duke.frontend;

import duke.command.*;
import duke.io.Input;
import duke.io.Output;
import duke.io.Parser;
import duke.io.Storage;

import duke.DukeException;

import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.tasklist.ToDo;
import duke.tasklist.Deadline;
import duke.tasklist.Event;

import java.util.ArrayList;

/**
 * The user interface which the user uses to interacts with duke, and manipulate the task list
 */
public class UserInterface {
    private boolean isAcceptingInput;
    private TaskList taskList;
    private Storage storage;
    private Input input;
    private Output standardOutput;
    private Output errorOutput;

    /**
     * Constructs the user interface for Duke
     */
    public UserInterface() {
        // setup input and output
        input = new Input(System.in);

        String lineBreak1 = "___________________________________________________________";
        String lineBreak2 = "‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾";

        errorOutput = new Output(System.out);
        errorOutput.setHeader(lineBreak1, " ☹ OOPS!!!");
        errorOutput.setFooter(lineBreak2);
        errorOutput.setLeftBorder("ERROR ");
        errorOutput.setLeftIndent(1);
        errorOutput.setWrapOn();

        standardOutput = new Output(System.out);
        standardOutput.setHeader(lineBreak1);
        standardOutput.setFooter(lineBreak2);
        standardOutput.setLeftBorder("      ");
        standardOutput.setLeftIndent(1);
        standardOutput.setWrapOn();

        storage = new Storage("DukeSave01.txt");

        // load default exisiting task list if any
        taskList = new TaskList();

        isAcceptingInput = true;
    }

    // helper method for determining width threshold for text wrapping in output
    private static int getWidth(int number) {
        return Integer.toString(number).length();
    }

    /**
     * Returns the boolean representing whether or not the user interface is accepting user input
     *
     * @return True is the user interface can accept user input, false otherwise
     */
    public boolean canAcceptInput() {
        return isAcceptingInput;
    }

    /**
     * Intructs the output to display the logo, and welcome message of duke, and to load the previously saved task list.
     *
     * @throws DukeException Exception thrown when error occurs while setting up duke
     */
    public void start() throws DukeException {
        Output logo = new Output(System.out);

        logo.addLine(" ____        _        ")
                .addLine("|  _ \\ _   _| | _____ ")
                .addLine("| | | | | | | |/ / _ \\")
                .addLine("| |_| | |_| |   <  __/")
                .addLine("|____/ \\__,_|_|\\_\\___|");
        logo.print();

        standardOutput.addLine("Hi! I'm Duke :)")
                .addLine("What can I do for you?");
        displayOutput();


        taskList = storage.loadTaskList();
    }

    /**
     * Displays the content in the standard output buffer
     */
    public void displayOutput() {
        standardOutput.print();
    }

    /**
     * Displays the error message when an exception in the user interface occurs and an exception is thrown
     *
     * @param ex The exception which was thrown when an error occurred
     */
    public void displayError(DukeException ex) {
        //flushes standard output otherwise future output may be wrong
        standardOutput.clear();

        // displays dukeexception error messages
        errorOutput.addLine(ex.getMessage());
        errorOutput.print();
    }

    /**
     * Reads the input from console and return the command it is parsed as.
     *
     * @return The command the input is parsed as if it can be parsed
     * @throws DukeException The exception thrown when the input cannot be properly parsed as a command
     */
    public Command readInput() throws DukeException {
        return Parser.parseAsCommand(input.get());
    }

    /**
     * Executes the command in the environment of the user interface
     *
     * @param command The command to be executed
     * @throws DukeException The exception thrown when a command cannot be executed
     */
    public void executeCommand(Command command) throws DukeException {
        // all commands passed to this method have all required parameter non-empty
        switch (command.getClass().getSimpleName()) {
        case "AddTaskCommand":
            executeCommand((AddTaskCommand) command);
            break;
        case "CompleteTaskCommand":
            executeCommand((CompleteTaskCommand) command);
            break;
        case "DeleteTaskCommand":
            executeCommand((DeleteTaskCommand) command);
            break;
        case "ShowListCommand":
            executeCommand((ShowListCommand) command);
            break;
        case "ExitCommand":
            executeCommand((ExitCommand) command);
            break;
        case "SearchCommand":
            executeCommand((SearchCommand) command);
        default:
            throw new DukeUnknownCommandException();
        }
    }

    private void executeCommand(AddTaskCommand command) throws DukeException {
        String[] parameters = Command.getArgumentsUsed(command);
        Task task;

        switch (Command.getTypeOf(command)) {
        case ADD_TODO:
            task = new ToDo(parameters[0]);
            break;
        case ADD_DEADLINE:
            try {
                task = new Deadline(parameters[0], Parser.parseDateTime(parameters[1]));
            } catch (DukeException ex) {
                task = new Deadline(parameters[0], parameters[1]);
            }
            break;
        case ADD_EVENT:
            try {
                task = new Event(parameters[0], Parser.parseDateTime(parameters[1]));
            } catch (DukeException e) {
                task = new Event(parameters[0], parameters[1]);
            }
            break;
        default:
            throw new DukeException("This task type is not supported yet");
        }

        taskList.add(task);

        standardOutput.addLine("Got it! I've added this task to the list:")
                .addLine(task.toString())
                .addLine("Now you have ",
                        Integer.toString(taskList.size()),
                        " task(s) in your list.");

        storage.save(taskList);
    }


    private void executeCommand(CompleteTaskCommand command) throws DukeException {
        String[] parameters = Command.getArgumentsUsed(command);
        Task task = taskList.complete(parameters[0]);

        standardOutput.addLine("Got it! I've marked this task as done:")
                .addLine(task.toString());

        storage.save(taskList);
    }

    private void executeCommand(DeleteTaskCommand command) throws DukeException {
        String[] parameters = Command.getArgumentsUsed(command);
        Task task = taskList.delete(parameters[0]);

        standardOutput.addLine("Got it! I've removed this task from the list:")
                .addLine(task.toString())
                .addLine("Now you have ",
                        Integer.toString(taskList.size()),
                        " task(s) in your list.");

        storage.save(taskList);
    }

    private void executeCommand(SearchCommand command) {
        String[] parameters = Command.getArgumentsUsed(command);
        ArrayList<Task> results = taskList.search(parameters[0]);

        if (results.size() > 0) {
            int count = 0;
            standardOutput.addLine("Here are the matching tasks in your list:");
            for (Task task : taskList.search(parameters[0])) {
                count++;
                standardOutput.addLine(Integer.toString(count), ". ", task.toString());
            }
        } else {
            standardOutput.addLine("There are no matching tasks in your list");
        }
    }

    private void executeCommand(ShowListCommand command) {
        if (taskList.size() == 0) {
            standardOutput.addLine("Your list is empty!");
        } else {
            int count = 0;
            standardOutput.addLine("Here are the task(s) in your list:");

            for (Task task : taskList.list()) {
                count++;
                standardOutput.addLine(
                        String.format("%0" + getWidth(taskList.size()) + "d", count), ". ", task.toString());
            }
        }
    }

    private void executeCommand(ExitCommand command) throws DukeException {
        isAcceptingInput = false;
        storage.save(taskList);
        standardOutput.addLine("Goodbye! Hope to see you again!");
    }
}
