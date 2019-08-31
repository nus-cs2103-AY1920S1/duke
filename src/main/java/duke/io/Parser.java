package duke.io;

import duke.command.*;
import duke.task.Deadline;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Interface used by duke to parse information.
 */
public class Parser {
    /**
     * Parses input text into respective command representation.
     * @param input User input string.
     * @return Appropriate command representation.
     * @throws UnsupportedOperationException If command not understood.
     * @throws ArrayIndexOutOfBoundsException If Event/Deadline details left blank.
     */
    public static Command parse(String input) throws UnsupportedOperationException, ArrayIndexOutOfBoundsException {
        String command = input.split(" ")[0];
        input = input.replace(command, "").trim();
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new MarkCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "todo":
            return new AddCommand(input);
        case "event":
            return new AddEventCommand(input);
        case "deadline":
            return new AddDeadlineCommand(input);
        default:
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Returns the index of the list to be managed.
     * @param input User command
     * @param size Size of task list
     * @return User value specified
     * @throws NumberFormatException If number out of range or invalid input
     */
    public static int getIndex(String input, int size) throws NumberFormatException {
        int value = Integer.parseInt(input.substring(input.length() - 1));
        if (value < 0 || value > size) {
            throw new NumberFormatException();
        }
        return value;
    }

    /**
     * Processes Input Event/Deadline to Description & Details.
     * @param input Task String (without command)
     * @return Array. Index 0 = Description. Index 1  = Details
     * @throws ArrayIndexOutOfBoundsException Command missing details
     */
    public static String[] getDetails(String input) throws ArrayIndexOutOfBoundsException {
        String[] desc = input.split("/");
        String[] temp = desc[1].split(" ");
        desc[1] = desc[1].replace(temp[0], "").trim();
        return desc;
    }

    /**
     * Returns java date object from input string.
     * @param details String representation of date.
     * @return Date object.
     * @throws ParseException If string representation not in proper format.
     */
    public static Date getAsDate(String details) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HHmm");
        return format.parse(details);
    }

    /**
     * Creates Task from stored task data.
     * @param code Stored task data.
     * @return Appropriate Task.
     * @throws ParseException Error in stored date data.
     */
    static Task init(String[] code) throws ParseException {
        boolean done = "1".equals(code[1]);
        switch (code[0]) {
        case "T":
            return new TodoTask(code[2], done);
        case "D":
            return new Deadline(code[2], Parser.getAsDate(code[3]), done);
        case "E":
            return new EventTask(code[2], Parser.getAsDate(code[3]), done);
        default:
            return null;
        }
    }
}
