package duke.core;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

import java.util.Arrays;

/**
 * Encapsulates a Parser object to deals with making sense of the user command.
 */
public class Parser {

    /** 2 attribute.
     * fullCommand represents the String of input from the user.
     * part represents an array of Strings splitted by " ".
     */
    private String fullCommand;
    private String[] part;

    /**
     * The constructor takes in a String and creates a new Parser object.
     * @param fullCommand the String of input from the user.
     */
    public Parser(String fullCommand) {
        this.fullCommand = fullCommand;
        this.part = fullCommand.split(" ");
    }

    Command parseCommand() throws DukeException {
        if (checkValidity()) {
            switch (getCommandType()) {
            case "list":
                return new ListCommand(fullCommand);
            case "done":
                return new DoneCommand(fullCommand);
            case "delete":
                return new DeleteCommand(fullCommand);
            case "find":
                return new FindCommand(fullCommand);
            case "todo":
                return new TodoCommand(fullCommand);
            case "deadline":
                return new DeadlineCommand(fullCommand);
            case "event":
                return new EventCommand(fullCommand);
            default:
                assert false : getCommandType();
            }
        }
        return new Command(fullCommand);
    }

    /**
     * Returns true if the full command is a valid command, or false otherwise.
     * @return boolean variable representing the validity of the command.
     * @throws DukeException if the command is invalid.
     */
    boolean checkValidity() throws DukeException {
        switch (getCommandType()) {
        case "list":
            break;
        case "done": case "delete":
            if (part.length < 2) {
                throw new DukeException("☹ OOPS!!! You need to enter an index.");
            }
            break;
        case "find":
            if (part.length < 2) {
                throw new DukeException("☹ OOPS!!! You need to enter a keyword to search");
            }
            break;
        case "todo": case "deadline": case "event":
            if (part.length < 2) {
                throw new DukeException("☹ OOPS!!! The description of a(n)" + getCommandType()
                        + "cannot be empty.");
            }
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what "
                    + "that means :-(");
        }
        return true;
    }

    /**
     * Returns the command type which is the first word of the full command.
     * @return String of command type.
     */
    public String getCommandType() {
        return part[0];
    }

    /**
     * Returns the index of the targeted task when there is a Done/Delete command.
     * @return integer of the task index.
     */
    public int[] getIndices() {
        if (part[1].contains("-")) {
            String[] range = part[1].split("-");
            int start = Integer.parseInt(range[0]);
            int end = Integer.parseInt(range[1]);
            int[] indices = new int[end - start + 1];
            for (int i = start; i <= end; i++) {
                indices[i - start] = i;
            }
            return indices;
        } else {
            String[] range = fullCommand.split("[, ]");
            int[] indices = Arrays.stream(range)
                                .filter(s -> !s.equals("") && !s.equals("delete") && !s.equals("done"))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            return indices;
        }
    }

    public String getKeyword() {
        return fullCommand.substring(fullCommand.indexOf(" ") + 1);
    }

    /**
     * Returns the activity name of a Todo task.
     * @return String of activity name of a Todo task.
     */
    public String getActivityNameWithoutTime() {
        return fullCommand.substring(fullCommand.indexOf(" ") + 1);
    }

    /**
     * Returns the activity name of a Deadline or Event task.
     * @return String of activity name of a Deadine or Event task.
     */
    public String getActivityNameWithTime() {
        return fullCommand.substring(fullCommand.indexOf(" ") + 1,
                fullCommand.indexOf("/"));
    }

    /**
     * Returns the deadline in a "deadline" command.
     * @return String of deadline.
     */
    public String getDeadline() {
        String[] deadline = fullCommand.split("by");
        return deadline[1];
    }

    /**
     * Returns the time of a Event task in a "event" command.
     * @return String of time of a Event task.
     */
    public String getTime() {
        String[] time = fullCommand.split("at");
        return time[1];
    }

}
