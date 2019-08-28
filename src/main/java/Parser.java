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
    public Parser (String fullCommand) {
        this.fullCommand = fullCommand;
        this.part = fullCommand.split(" ");
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
    public int getIndex() {
        return Integer.parseInt(part[1]);
    }

    public String getKeyword() {
        return fullCommand.substring(fullCommand.indexOf(" ")+1);
    }

    /**
     * Returns the activity name of a Todo task.
     * @return String of activity name of a Todo task.
     */
    public String getActivityNameWithoutTime() {
        return fullCommand.substring(fullCommand.indexOf(" ")+1);
    }

    /**
     * Returns the activity name of a Deadline or Event task.
     * @return String of activity name of a Deadine or Event task.
     */
    public String getActivityNameWithTime() {
        return fullCommand.substring(fullCommand.indexOf(" ")+1,
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

    /**
     * Returns true if the full command is a valid command, or false otherwise.
     * @return boolean variable representing the validity of the command.
     * @throws DukeException if the command is invalid.
     */
    public boolean checkValidity() throws DukeException {
        switch (getCommandType()) {
<<<<<<< HEAD
            case "list":
                break;
            case "done": case "delete":
                // check whether a "done/delete" command contains an integer.
                if (part.length < 2) {
                    throw new DukeException("☹ OOPS!!! You need to enter an index.");
                }
        case "find":
            if (part.length < 2) {
                throw new DukeException("☹ OOPS!!! You need to enter a word "
                        + "for search.");
            }
            case "todo":
                // check whether task description is empty.
                if (part.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a todo " +
                            "cannot be empty.");
                }
                break;
            case "deadline":
                // check whether task description is empty.
                if (part.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline " +
                            "cannot be empty.");
                }
                break;
            case "event":
                // check whether task description is empty.
                if (part.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of an event " +
                            "cannot be empty.");
                }
                break;
            default:
                // random inputs.
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know " +
                        "what that means :-(");
=======
        case "list":
            break;
        case "done": case "delete":
            if (part.length < 2) {
                throw new DukeException("☹ OOPS!!! You need to enter an index.");
            }
        case "todo":
            // check task description.
            if (part.length < 2) {
                throw new DukeException("☹ OOPS!!! The description of a todo "
                        + "cannot be empty.");
            }
            break;
        case "deadline":
            if (part.length < 2) {
                throw new DukeException("☹ OOPS!!! The description of a deadline "
                        + "cannot be empty.");
            }
            break;
        case "event":
            if (part.length < 2) {
                throw new DukeException("☹ OOPS!!! The description of an event "
                        + "cannot be empty.");
            }
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what "
                    + "that means :-(");
>>>>>>> branch-A-CodingStandard
        }
        return true;
    }
}
