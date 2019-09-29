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

    /** 3 attribute.
     * fullCommand represents the String of input from the user.
     * part represents an array of Strings splitted by " ".
     * numOfTasks represents the number of existing tasks in the list.
     */
    private String fullCommand;
    private String[] part;
    private int numOfTasks;

    /**
     * Creates a new Parser object.
     * @param fullCommand command input from the user.
     * @param numOfTasks the number of existing tasks in the list.
     */
    public Parser(String fullCommand, int numOfTasks) {
        this.fullCommand = fullCommand;
        this.part = fullCommand.split(" ");
        this.numOfTasks = numOfTasks;
    }

    /**
     * Creates and Returns new Command object according to the command type.
     * @return a new Command object
     * @throws DukeException if the command is invalid.
     */
    Command parseCommand() throws DukeException {
        if (checkValidity()) {
            switch (getCommandType()) {
            case "list":
                return new ListCommand(fullCommand);
            case "done":
                return new DoneCommand(fullCommand, getIndices());
            case "delete":
                return new DeleteCommand(fullCommand, getIndices());
            case "find":
                return new FindCommand(fullCommand, getKeyword());
            case "todo":
                return new TodoCommand(fullCommand, getActivityNameWithoutTime());
            case "deadline":
                return new DeadlineCommand(fullCommand, getActivityNameWithTime(), getDeadline());
            case "event":
                return new EventCommand(fullCommand, getActivityNameWithTime(), getTime());
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
    private boolean checkValidity() throws DukeException {
        switch (getCommandType()) {
        case "list":
            break;
        case "done": case "delete":
            if (isIncompleteCommand()) {
                throw new DukeException("☹ OOPS!!! You need to enter an index or a range.");
            }
            if (isInvalidIndex()) {
                throw new DukeException("☹ OOPS!!! index/range entered is invalid.");
            }
            break;
        case "find":
            if (isIncompleteCommand()) {
                throw new DukeException("☹ OOPS!!! You need to enter a keyword to search");
            }
            break;
        case "todo":
            if (isIncompleteCommand()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case "deadline":
            if (isIncompleteCommand()) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            if (isWithoutDeadline()) {
                throw new DukeException("☹ OOPS!!! you need to enter a deadline");
            }
            break;
        case "event":
            if (isIncompleteCommand()) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            if (isWithoutTime()) {
                throw new DukeException("☹ OOPS!!! you need to enter a time");
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
     * Returns true if the command is incomplete and lacks details.
     * @return true if the command is incomplete and lacks details.
     */
    private boolean isIncompleteCommand() {
        return part.length < 2;
    }

    /**
     * Returns true if the index / range entered is invalid.
     * @return true if the index / range entered is invalid.
     */
    private boolean isInvalidIndex() {
        int[] indices = getIndices();
        for (int i : indices) {
            return i <= 0 || i > numOfTasks;
        }
        return false;
    }

    /**
     * Returns true if a deadline of a Deadline object is missing.
     * @return true if deadline component is missing.
     */
    private boolean isWithoutDeadline() {
        return !fullCommand.contains("by");
    }

    /**
     * Returns true if a time of an Event object is missing.
     * @return true if time component is missing.
     */
    private boolean isWithoutTime() {
        return !fullCommand.contains("at");
    }

    /**
     * Returns the index of the targeted task when there is a Done/Delete command.
     * @return integer of the task index.
     */
    private int[] getIndices() {
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
            return Arrays.stream(range)
                                .filter(s -> !s.equals("") && !s.equals("delete") && !s.equals("done"))
                                .mapToInt(Integer::parseInt)
                                .toArray();
        }
    }

    /**
     * Returns the keyword for searching.
     * @return String of keyword.
     */
    private String getKeyword() {
        return fullCommand.substring(fullCommand.indexOf(" ") + 1);
    }

    /**
     * Returns the activity name of a Todo task.
     * @return String of activity name of a Todo task.
     */
    private String getActivityNameWithoutTime() {
        return fullCommand.substring(fullCommand.indexOf(" ") + 1);
    }

    /**
     * Returns the activity name of a Deadline or Event task.
     * @return String of activity name of a Deadine or Event task.
     */
    private String getActivityNameWithTime() {
        return fullCommand.substring(fullCommand.indexOf(" ") + 1,
                fullCommand.indexOf("/"));
    }

    /**
     * Returns the deadline in a "deadline" command.
     * @return String of deadline.
     */
    private String getDeadline() {
        String[] deadline = fullCommand.split("by");
        return deadline[1];
    }

    /**
     * Returns the time of a Event task in a "event" command.
     * @return String of time of a Event task.
     */
    private String getTime() {
        String[] time = fullCommand.split("at");
        return time[1];
    }
}
