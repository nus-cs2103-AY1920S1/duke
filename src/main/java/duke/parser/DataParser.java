package duke.parser;

import java.util.Scanner;

import duke.command.AddDeadlineTaskCommand;
import duke.command.AddEventTaskCommand;
import duke.command.AddTagCommand;
import duke.command.AddToDoTaskCommand;
import duke.command.Command;
import duke.command.CompleteTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.EditTaskDateCommand;
import duke.command.EditTaskNameCommand;
import duke.command.EndCommand;
import duke.command.FindTaggedTaskCommand;
import duke.command.FindTaskCommand;
import duke.command.ListCommandsCommand;
import duke.command.ListTaskCommand;
import duke.exception.DukeException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEditTaskException;
import duke.exception.InvalidEventException;
import duke.exception.InvalidKeywordException;
import duke.exception.InvalidTagException;
import duke.exception.InvalidTaskIndexException;
import duke.exception.InvalidToDoException;
import duke.exception.UnknownCommandException;
import duke.task.TaskList;

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
        } else if (shouldTagTask()) {
            return new AddTagCommand();
        } else if (isNotProperEditCommand()) {
            throw new UnknownCommandException();
        } else if (shouldEditTaskName()) {
            return new EditTaskNameCommand();
        } else if (shouldEditTaskDate()) {
            return new EditTaskDateCommand();
        } else if (shouldFindTaggedTask()) {
            return new FindTaggedTaskCommand();
        } else if (shouldListCommands()) {
            return new ListCommandsCommand();
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
            try {
                Integer.parseInt(index);
            } catch (Exception e) {
                throw new InvalidTaskIndexException();
            }
            int taskIndex = Integer.parseInt(index) - 1;
            return taskIndex;
        }
    }

    /**
     * Checks if the data given represents a task with no name.
     * @param data The data provided by the user input.
     * @return Returns false if the data provided gives a name for the task.
     */
    public boolean isEmptyData(String data) {
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
     * Checks if a name is given for the to do task.
     * @param data the to do data given.
     * @return if there is a name given.
     */
    private boolean isTodoNameProvided(String[] data) {
        return data.length >= 2;
    }

    private boolean isNotProperEditCommand() {
        return input.trim().equals("edit");
    }

    /**
     * Parses the user input to return the name of the ToDo Task.
     * @return the name of the ToDo Task.
     * @throws InvalidToDoException If the name of the task is not provided.
     */
    public String[] parseToDoData() throws InvalidToDoException, UnknownCommandException {
        String[] data = this.input.split(" ");
        if (!isTodoNameProvided(data)) {
            throw new InvalidToDoException();
        }

        return data;
    }

    /**
     * Prases the input and returns the index and tag as an array of Strings.
     * @return an array of Strings providing the index and the tag in this order.
     * @throws InvalidTagException if no data is provided.
     */
    public String[] parseTagData() throws InvalidTagException {
        String tagData = input.substring(4).trim();
        if (isEmptyData(tagData)) {
            throw new InvalidTagException("Please provide an index and a tag!");
        }
        return tagData.split(" ");
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

        int index = Integer.parseInt(data.split(" ")[0]);
        return (index < 1 || index > TaskList.getNumberOfTasks());
    }

    /**
     * Parses the user input to return the name of the Deadline Task.
     * @return the name and the date of the Deadline Task in the form of an array.
     * @throws InvalidDeadlineException If the name or the date of the task is not provided.
     */
    public String[] parseDeadlineData() throws InvalidDeadlineException {
        String data = this.input.substring(8).trim();
        if (isEmptyData(data)) {
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
        if (isEmptyData(data)) {
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
     * Checks if the user has given a keyword to find matching tasks.
     * @param data the keyword data given.
     * @return true if there is a keyword given.
     */
    private boolean hasKeyWord(String[] data) {
        return data.length > 1;
    }

    /**
     * Returns the keyword to start the search of matching tasks.
     * @return the matching keyword.
     */
    public String findKeyWord() throws InvalidKeywordException {
        String[] data = this.input.split(" ");
        if (!hasKeyWord(data)) {
            throw new InvalidKeywordException();
        }
        if (isEmptyData(data[1])) {
            throw new InvalidKeywordException();
        }
        return data[1];
    }

    /**
     * Returns the tag to start the search of matching tasks.
     * @return the matching tag.
     */
    public String findTag() throws InvalidKeywordException {
        String[] data = this.input.split(" ");
        if (!hasKeyWord(data)) {
            throw new InvalidKeywordException();
        }
        if (isEmptyData(data[1])) {
            throw new InvalidKeywordException();
        }
        return data[1];
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
        return input.split(" ")[0].equals("done");
    }

    /**
     * Checks if the user wishes to delete a task.
     * @return True if the input starts with "delete".
     */
    public boolean shouldDeleteTask() {
        return input.split(" ")[0].equals("delete");
    }

    /**
     * Checks if the user wishes to add a ToDo Task.
     * @return True if the input starts with "todo".
     */
    public boolean shouldAddToDoTask() {
        return input.split(" ")[0].equals("todo");
    }

    /**
     * Checks if the user wishes to add a Deadline Task.
     * @return True if the input starts with "deadline".
     */
    public boolean shouldAddDeadlineTask() {
        return input.split(" ")[0].equals("deadline");
    }

    /**
     * Checks if the user wishes to add an Event Task.
     * @return True if the input starts with "event".
     */
    public boolean shouldAddEventTask() {
        return input.split(" ")[0].equals("event");
    }

    /**
     * Checks if the user wishes to find a task based on a keyword.
     * @return True if the input starts with "find".
     */
    public boolean shouldFindTask() {
        return input.split(" ")[0].equals("find");
    }

    /**
     * Checks if the user wishes to find a task based on a tag.
     * @return True if the input starts with "findTag".
     */
    public boolean shouldFindTaggedTask() {
        return input.split(" ")[0].equals("findTag");
    }

    /**
     * Checks if the user wishes to tag a task.
     * @return true if the input starts with "tag".
     */
    public boolean shouldTagTask() {
        return input.split(" ")[0].equals("tag");
    }

    /**
     * Checks if the user wishes to edit the task name.
     * @return true if the input starts with "edit name".
     */
    public boolean shouldEditTaskName() {
        return input.split(" ")[0].equals("edit")
                && input.split(" ")[1].equals("name");
    }

    /**
     * Checks if the user wishes to edit a task date or not.
     * @return true if the input starts with "edit date".
     */
    public boolean shouldEditTaskDate() {
        return input.split(" ")[0].equals("edit")
                && input.split(" ")[1].equals("date");
    }

    /**
     * Checks if the user wishes to list the commands available.
     * @return true if the input is "help".
     */
    private boolean shouldListCommands() {
        return input.equals("help");
    }

}
