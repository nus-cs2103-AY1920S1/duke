package duke.parser;

import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.AddCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.UndoCommand;

import duke.exception.DukeException;

/**
 * Represents a parser that parses a line, returning a Command instance.
 */
public class Parser {
    private static final String UNKNOWN_COMMAND = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String UNKNOWN_TASK = "OOPS!!! The description of a task cannot be empty.";
    private static final String UNKNOWN_DEADLINE = "OOPS!!! The description of a deadline cannot be empty.";
    private static final String UNKNOWN_EVENT = "OOPS!!! The description of an event cannot be empty.";
    private static final String BAD_COMMAND = "Your command needs to have more parameters";

    /**
     * Returns a Command instance of the specified line.
     *
     * @param line Line to be parsed.
     * @return an instance of a Command specified by the line .
     * @throws DukeException when line does not meet expected parameters.
     */
    public static Command parse(String line) throws DukeException {
        if ("bye".equals(line)) {
            return new ByeCommand();
        } else if ("list".equals(line)) {
            return new ListCommand();
        } else {
            String[] strArr = line.split(" ");
            String command = strArr[0];
            int index;
            switch (command) {
            case "todo":
                if (strArr.length < 2) {
                    throw new DukeException(UNKNOWN_TASK);
                }
                return new AddCommand("todo", line.substring(5));
            case "deadline":
                String[] deadline = deadlineEventString(strArr, true);
                return new AddCommand("deadline", deadline[0], deadline[1]);
            case "event":
                String[] event = deadlineEventString(strArr, false);
                return new AddCommand("event", event[0], event[1]);
            case "done":
                if (strArr.length < 2) {
                    throw new DukeException(BAD_COMMAND);
                }
                index = Integer.parseInt(strArr[1]);
                return new DoneCommand(index);
            case "delete":
                if (strArr.length < 2) {
                    throw new DukeException(BAD_COMMAND);
                }
                index = Integer.parseInt(strArr[1]);
                return new DeleteCommand(index);
            case "find":
                if (strArr.length < 2) {
                    throw new DukeException(BAD_COMMAND);
                }
                return new FindCommand(line.substring(5));
            case "undo":
                return new UndoCommand();
            default:
                throw new DukeException(UNKNOWN_COMMAND);
            }
        }
    }

    /**
     * Helper method that returns a string for Event or Deadline instance creation.
     *
     * @param arr      String Array.
     * @param deadline true if deadline, false if event.
     * @return String[] with 2 elements - first is name, second is datetime.
     * @throws DukeException if expected parameters are not met.
     */
    private static String[] deadlineEventString(String[] arr, boolean deadline) throws DukeException {
        String[] res = new String[2];
        StringBuilder sb = new StringBuilder();
        int divide = 0;
        for (int i = 1; i < arr.length; i++) {
            if (deadline && arr[i].equals("/by") || !deadline && arr[i].equals("/at")) {
                divide = i;
                break;
            }
            sb.append(arr[i]);
            sb.append(" ");
        }
        res[0] = sb.toString().trim();
        if (res[0].isEmpty()) {
            throw new DukeException(deadline ? UNKNOWN_DEADLINE : UNKNOWN_EVENT);
        }
        sb = new StringBuilder();
        for (int i = divide + 1; i < arr.length; i++) {
            sb.append(arr[i]);
            sb.append(" ");
        }
        res[1] = sb.toString().trim();
        return res;
    }
}
