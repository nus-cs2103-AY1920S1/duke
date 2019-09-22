package storage;

import exceptions.InvalidCommandException;
import exceptions.InvalidInputException;
import exceptions.MissingInputException;

import task.DukeDate;
import task.Deadline;
import task.Event;
import task.Task;
import task.DukeTime;
import task.Todo;

public class Parser {

    private Task task = null;
    private static int count = 0;

    /**
     * Constructor for a Parser object used to process input and strings.
     */
    public Parser() {

    }

    /**
     * Takes in line of information to process into commands for program to read.
     *
     * @param line task description or information needed for processing.
     * @return command used for updating tasks.
     * @throws InvalidCommandException when program gives an invalid command type.
     */
    public Command process(String line) {
        String[] commands = line.split(" ");
        String first = commands[0];
        switch (first) {
        case "bye":
            return new Command(CommandType.EXIT);
        case "list":
            return new Command(CommandType.PRINTLIST);
        case "todo":
        case "deadline":
        case "event":
            return new Command(CommandType.ADD, line);
        case "done":
            return new Command(CommandType.DONE, line);
        case "delete":
            return new Command(CommandType.DELETE, line);
        case "find":
            return new Command(CommandType.FIND, line);
        case "update":
            return new Command(CommandType.UPDATE, line);
        default:
            return new Command(CommandType.INVALID);
        }
    }

    /**
     * Processes command to get information for update.
     *
     * @param command Command taken in for processing.
     * @return Array of Strings containing the various segments of information required.
     * @throws MissingInputException when description for the update is incomplete.
     * @throws InvalidInputException when an invalid update type is included.
     */
    public String[] getUpdateInfo(Command command) throws MissingInputException, InvalidInputException {
        String desc = command.getDescription();
        String[] description = desc.split(" \\| ");
        if (description.length <= 3) {
            throw new MissingInputException("Description for update is incomplete!\n"
                    + "Instruction should be in the form Update | [TaskNo] | [Type] | [Info]");
        }
        String updateType = description[2];
        switch (updateType) {
        case "desc":
        case "time":
        case "date":
            //allow cases to fall through
            String[] info = new String[3];
            for (int i = 0; i < 3; i++) {
                info[i] = description[i + 1];
            }
            return info;
        default:
            throw new InvalidInputException("This is not an update type.\n"
                + "An update type should be: \n"
                + "desc, time or date.");
        }
    }

    /**
     * Processes String to retrieve keyword for search.
     *
     * @param command command for finding matching tasks.
     * @return keyword for matching tasks.
     */
    public String getKeyword(Command command) {
        String line = command.getDescription();
        String[] description = line.split(" ");
        return description[1];
    }

    /**
     * Creates task from given Command.
     *
     * @param command command for task to be created.
     * @return task created from the command.
     * @throws MissingInputException when command's description is incomplete.
     * @throws InvalidInputException when date/time description is not in given format.
     */
    public Task createTask(Command command) throws MissingInputException, InvalidInputException {
        String line = command.getDescription();
        String[] description = line.split(" ");
        String eventType = description[0];
        count++;
        assert count > 0;
        return createNewTask(count, eventType, description);
    }

    /**
     * Processes command description for task number of related task.
     *
     * @param command command for task to be marked as done/deleted.
     * @return task number
     * @throws MissingInputException when command's description is incomplete.
     */
    public int getTaskNo(Command command) throws MissingInputException {
        String line = command.getDescription();
        String[] description = line.split(" ");
        if (description.length <= 1) {
            throw new MissingInputException(Task.MISSING_DESC_ERROR_MESSAGE);
        }
        return Integer.parseInt(description[1]);
    }

    /**
     * Creates task by processing information given for task.
     * Intermediate operation for Parser's createTask method.
     *
     * @param taskNo Task's number in the list.
     * @param taskType tasks of type Todo/Deadline/Event.
     * @param arr String array that contains task description that has been processed.
     * @return Task created from given inputs.
     * @throws MissingInputException when description is incomplete.
     */
    Task createNewTask(int taskNo, String taskType, String[] arr) throws MissingInputException, InvalidInputException {
        boolean firstInDescription = true;
        String desc = "";
        DukeDate date = null;
        DukeTime time = null;
        for (int i = 1; i < arr.length; i++) {
            if (firstInDescription) {
                desc += arr[i];
                firstInDescription = false;
            } else if (arr[i].startsWith("/")) {
                break;
            } else {
                desc += " " + arr[i];
            }
        }
        switch (taskType) {
        case "todo":
            if (desc.equals("")) {
                throw new MissingInputException(Task.MISSING_DESC_ERROR_MESSAGE);
            }
            task = new Todo(taskNo, desc, "T");
            break;
        case "event":
            if (arr.length <= 2) {
                throw new MissingInputException(Task.MISSING_DATE_TIME_MESSAGE);
            }
            date = DukeDate.processDate(arr[arr.length - 2]);
            time = DukeTime.processTime(arr[arr.length - 1]);
            task = new Event(taskNo, desc, date, time, "E");
            break;
        case "deadline":
            if (arr.length <= 2) {
                throw new MissingInputException(Task.MISSING_DATE_TIME_MESSAGE);
            }
            date = DukeDate.processDate(arr[arr.length - 2]);
            time = DukeTime.processTime(arr[arr.length - 1]);
            task = new Deadline(taskNo, desc, date, time, "D");
            break;
        default:
            assert false; //only three types of tasks should be created!
        }
        return task;
    }

}
