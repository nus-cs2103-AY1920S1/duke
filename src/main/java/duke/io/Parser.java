package duke.io;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import duke.command.*;
import duke.task.*;

public class Parser {
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
     * Returns the index of the list to be managed
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
     * Processes Input Event/Deadline to Description & Details
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

    public static Date getAsDate(String details) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HHmm");
        return format.parse(details);
    }

    /**
     * Creates Task from stored task data
     * @param code Stored task data
     * @return Task
     * @throws ParseException Error in stored date data
     */
    static Task init(String[] code) throws ParseException {
        boolean done = "1".equals(code[1]);
        switch (code[0]) {
        case "T":
            return new Todo(code[2], done);
        case "D":
            return new Deadline(code[2], Parser.getAsDate(code[3]), done);
        case "E":
            return new Event(code[2], Parser.getAsDate(code[3]), done);
        default:
            return null;
        }
    }
}
