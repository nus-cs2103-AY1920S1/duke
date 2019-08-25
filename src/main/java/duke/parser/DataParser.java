package duke.parser;

import duke.command.Command;
import duke.command.AddDeadlineTaskCommand;
import duke.command.AddEventTaskCommand;
import duke.command.AddToDoTaskCommand;
import duke.command.CompleteTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.EndCommand;
import duke.command.ListTaskCommand;

import java.util.Scanner;
import duke.exception.DukeException;
import duke.exception.UnknownCommandException;
import duke.exception.InvalidTaskIndexException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.exception.InvalidToDoException;

/**
 * Represents a Data Parser to parse in all user input provided.
 */
public class DataParser {

    private Scanner sc;
    private String input;

    /**
     * Constructs a new DataParser to read in the user input/
     */
    public DataParser() {
        this.sc = new Scanner(System.in);
        this.input = "";
    }

    /**
     * To check the user has anymore data left unread.
     * If there is anymore data left unread, return true.
     * @return Returns true if there is data left unread.
     */
    public boolean hasAnymoreData() {
        return sc.hasNextLine();
    }

    /**
     * Reads the input line.
     */
    public void readInput() {
        this.input = sc.nextLine();
    }

    /**
     * Parses the User Input data and returns a Command based on the first word given by the user.
     * Inputs should start with "bye", "delete", "done" etc.
     * @return A Command based on the user input.
     * @throws DukeException If the user input is invalid.
     */
    public Command findCommand() throws DukeException {
        if (shouldEndParsing()) {
            return new EndCommand();
        } else if (shouldListTasks()) {
            return new ListTaskCommand();
        } else if (shouldCompleteTask()) {
            return new CompleteTaskCommand();
        } else if (shouldDeleteTask()) {
            return new DeleteTaskCommand();
        } else if (shouldAddToDoTask()) {
            return new AddToDoTaskCommand();
        } else if (shouldAddDeadlineTask()) {
            return new AddDeadlineTaskCommand();
        } else if (shouldAddEventTask()) {
            return new AddEventTaskCommand();
        } else {
            throw new UnknownCommandException();
        }
    }

    /**
     * Returns the Index of the Task involved from the user input.
     * @return The Index of the Task.
     * @throws InvalidTaskIndexException If no index or invalid index is provided.
     */
    public int getTaskIndex() throws InvalidTaskIndexException {
        String[] parsedData = input.split(" ");
        if (parsedData.length < 2) {
            throw new InvalidTaskIndexException();
        } else {
            String index = parsedData[1];
            int taskIndex = Integer.parseInt(index) - 1;
            return taskIndex;
        }
    }

    /**
     * Checks if the data given represents a task with no name.
     * @param data The data provided by the user input.
     * @return Returns false if the data provided gives a name for the task.
     */
    public boolean isEmptyTask(String data) {
        return data.equals("");
    }

    /**
     * Checks if the user has included the "/by" regex to allow parsing of the date for the Deadline Task.
     * @param data The data provided by the user input.
     * @return Returns false if the data provided does not contain the "/by" regex.
     */
    public boolean isDeadlineTaskValid(String data) {
        return data.contains("/by");
    }

    /**
     * Checks if the user has included the "/att" regex to allow parsing of the date for the Event Task.
     * @param data The data provided by the user input.
     * @return Returns false if the data provided does not contain the "/at" regex.
     */
    public boolean isEventTaskValid(String data) {
        return data.contains("/at");
    }

    /**
     * Parses the user input to return the name of the ToDo Task.
     * @return the name of the ToDo Task.
     * @throws InvalidToDoException If the name of the task is not provided.
     */
    public String parseToDoData() throws InvalidToDoException {
        String data = this.input.substring(4);
        if (isEmptyTask(data)) {
            throw new InvalidToDoException();
        }

        return data.trim();
    }

    /**
     * Parses the user input to return the name of the Deadline Task.
     * @return the name and the date of the Deadline Task in the form of an array.
     * @throws InvalidDeadlineException If the name or the date of the task is not provided.
     */
    public String[] parseDeadlineData() throws InvalidDeadlineException {
        String data = this.input.substring(8).trim();
        if (isEmptyTask(data)) {
            throw new InvalidDeadlineException("The description of a deadline cannot be empty.");
        } else if (!isDeadlineTaskValid(data)) {
            throw new InvalidDeadlineException("Please include the time of a deadline.");
        }

        String[] taskData = data.split(" /by ");
        if (taskData.length == 1) {
            throw new InvalidDeadlineException("The time of a deadline cannot be empty.");
        }

        return taskData;
    }

    /**
     * Parses the user input to return the name of the Event Task.
     * @return the name and the date of the Event Task in the form of an array.
     * @throws InvalidEventException If the name or the date of the task is not provided.
     */
    public String[] parseEventDate() throws InvalidEventException {
        String data = this.input.substring(5).trim();
        if (isEmptyTask(data)) {
            throw new InvalidEventException("The description of an event cannot be empty.");
        } else if (!isEventTaskValid(data)) {
            throw new InvalidEventException("Please include the time of an event.");
        }

        String[] taskData = data.split(" /at ");
        if (taskData.length == 1) {
            throw new InvalidEventException("The time of an event cannot be empty.");
        }

        return taskData;
    }

    /**
     * Checks if the user input indicates that the data parser should end the parsing of user input.
     * @return Returns true if input is "bye".
     */
    public boolean shouldEndParsing() {
        return input.equals("bye");
    }

    /**
     * Checks if the user input indicates that the tasks should be listed out.
     * @return Returns true if input is "list".
     */
    public boolean shouldListTasks() {
        return input.equals("list");
    }

    /**
     * Checks if the user input wishes to complete a task.
     * @return Returns true if the input starts with "done".
     */
    public boolean shouldCompleteTask() {
        return input.startsWith("done");
    }

    /**
     * Checks if the user wishes to delete a task.
     * @return True if the input starts with "delete".
     */
    public boolean shouldDeleteTask() {
        return input.startsWith("delete");
    }

    /**
     * Checks if the user wishes to add a ToDo Task.
     * @return True if the input starts with "todo".
     */
    public boolean shouldAddToDoTask() {
        return input.startsWith("todo");
    }

    /**
     * Checks if the user wishes to add a Deadline Task.
     * @return True if the input starts with "deadline".
     */
    public boolean shouldAddDeadlineTask() {
        return input.startsWith("deadline");
    }

    /**
     * Checks if the user wishes to add an Event Task.
     * @return True if the input starts with "event".
     */
    public boolean shouldAddEventTask() {
        return input.startsWith("event");
    }

}
