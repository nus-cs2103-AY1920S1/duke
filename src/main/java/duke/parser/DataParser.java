package duke.parser;

import duke.command.Command;
import duke.command.CompleteTaskCommand;
import duke.command.EndCommand;
import duke.command.ListTaskCommand;
import duke.command.AddDeadlineTaskCommand;
import duke.command.AddEventTaskCommand;
import duke.command.AddToDoTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.EditTaskDateCommand;
import duke.command.EditTaskNameCommand;
import duke.command.FindTaskCommand;
import duke.exception.DukeException;
import duke.exception.UnknownCommandException;
import duke.exception.InvalidKeywordException;
import duke.exception.InvalidTaskIndexException;
import duke.exception.InvalidToDoException;
import duke.exception.InvalidEditTaskException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;

import java.util.Scanner;

;

/**
 * Represents a Data Parser to parse in all user input provided.
 */
public class DataParser {

    private Scanner sc;
    private String input;

    /**
     * Constructs a new DataParser to read in the user input.
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
     * Reads the given input line.
     * @param input The given input line.
     */
    public void readInput(String input) {
        this.input = input;
    }

    /**
     * Parses the User Input data and returns a Command based on the first word given by the user.
     * Inputs should start with "bye", "delete", "done" etc.
     * @return A Command based on the user input.
     * @throws DukeException If the user input is invalid.
     */
    public Command findTaskCommand() throws DukeException {
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
        } else if (shouldFindTask()) {
            return new FindTaskCommand();
        } else if (shouldEditTaskName()) {
            return new EditTaskNameCommand();
        } else if (shouldEditTaskDate()) {
            return new EditTaskDateCommand();
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
     * Checks if the data given does not provide the name to edit the task.
     * @param data the data provided by the user input.
     * @return false if the data provided does not give a name to edit the task.
     */
    public boolean isNewNameMissing(String data) {
        return data.split(" ").length == 1;
    }

    /**
     * Checks if the data given does not provide the date to edit the task.
     * @param data the data provided by the user input.
     * @return false if the data provided does not give a date to edit the task.
     */
    public boolean isNewDateMissing(String data) {
        return data.split(" ").length <= 1;
    }

    /**
     * Checks if the data given does not provide the time to edit the task.
     * @param data the data provided by the user input.
     * @return false if the data provided does not give a time to edit the task.
     */
    public boolean isNewTimeMissing(String data) {
        return data.split(" ").length <= 2;
    }


    /**
     * Checks if the data given represents an empty input or not.
     * @param data The data provided by the user input.
     * @return Returns false if the data provided gives a name for the task.
     */
    public boolean isEmptyInput(String data) {
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
     * Parses the edited task data based its name and given index.
     * @return an array containing the index and the new name of the task.
     * @throws InvalidEditTaskException if no name or index or invalid index is given.
     */
    public String[] parseEditTaskNameData() throws InvalidEditTaskException {
        String data = this.input.substring(9).trim();
        if (isEmptyInput(data) || isInvalidIndex(data)) {
            throw new InvalidEditTaskException("Please key in a valid index!");
        } else if (isNewNameMissing(data)) {
            throw new InvalidEditTaskException("Please key in a name!");
        }

        return data.split(" ");
    }

    /**
     * Parses the edited task data based its date and given index.
     * @return an array containing the index and the new name of the task.
     * @throws InvalidEditTaskException if no name or index or invalid index is given.
     */
    public String[] parseEditTaskDateData() throws InvalidEditTaskException {
        String data = this.input.substring(9).trim();
        if (isEmptyInput(data) || isInvalidIndex(data)) {
            throw new InvalidEditTaskException("Please key in a valid index!");
        } else if (isNewDateMissing(data)) {
            throw new InvalidEditTaskException("Please key in a date!");
        } else if (isNewTimeMissing(data)) {
            throw new InvalidEditTaskException("Please key in a time!");
        }

        return data.split(" ");
    }

    /**
     * Checks if the data given is a valid index or not.
     * @return true if the index is invalid
     */
    private boolean isInvalidIndex(String data) {
        try {
            Integer.parseInt(data.split(" ")[0]);
        } catch (Exception e) {
            return true;
        }

        return false;
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
    public String findKeyWord() throws InvalidKeywordException {
        String data = this.input.substring(5).trim();
        if (data.equals("")) {
            throw new InvalidKeywordException();
        } else {
            return data;
        }
    }

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

    /**
     * Checks if the user wishes to find the tasks or not.
     * @return true if the input starts with "find".
     */
    public boolean shouldFindTask() {
        return input.startsWith("find");
    }

    /**
     * Checks if the user wishes to edit a task name or not.
     * @return true if the input starts with "edit name".
     */
    public boolean shouldEditTaskName() {
        return input.startsWith("edit name");
    }

    /**
     * Checks if the user wishes to edit a task date or not.
     * @return true if the input starts with "edit date".
     */
    public boolean shouldEditTaskDate() {
        return input.startsWith("edit date");
    }
}
