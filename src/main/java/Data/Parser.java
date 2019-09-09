package Data;

import Exceptions.InvalidCommandException;
import Exceptions.InvalidInputException;
import Exceptions.MissingInputException;
import Task.*;

public class Parser {

    private Task task = null;
    private static int count = 0;

    /**
     * Constructor for a Parser object used to process input and strings.
     */
    public Parser(){};

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
        default:
            return new Command(CommandType.INVALID);
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
    public Task createTask(Command command) throws InvalidInputException, MissingInputException {
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
    public int getTaskNo(Command command) throws MissingInputException{
        String line = command.getDescription();
        String[] description = line.split(" ");
        String eventType = description[0];
            if (description.length <= 1) {
                throw new MissingInputException(eventType);
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
    Task createNewTask(int taskNo, String taskType, String[] arr) throws MissingInputException {
        boolean firstInDescription = true;
        String desc = "";
        Date date = null;
        Time time = null;
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
                throw new MissingInputException("T");
            }
            task = new Todo(taskNo, desc, "T");
            break;
        case "event":
            if (arr.length <= 2) {
                throw new MissingInputException("Date/Time");
            }
            date = Date.processDate(arr[arr.length-2]);
            time = Time.processTime(arr[arr.length-1]);
            task = new Event(taskNo, desc, date, time, "E");
            break;
        case "deadline":
            if (arr.length <= 2) {
                throw new MissingInputException("Date/Time");
            }
            date = Date.processDate(arr[arr.length-2]);
            time = Time.processTime(arr[arr.length-1]);
            task = new Deadline(taskNo, desc, date, time, "D");
            break;
        default:
            assert false; //only three types of tasks should be created!
        }
        return task;
    }

}
