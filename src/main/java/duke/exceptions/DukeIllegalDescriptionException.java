package duke.exceptions;

/**
 * The class is to deal with the illegal description of input after the commands.
 */
public class DukeIllegalDescriptionException extends Exception {

    /**
     * The constructor of the class, throw the exception of invalid description after the specific command.
     *
     * @param cmd The command inputted by users.
     */
    public DukeIllegalDescriptionException(String cmd) {
        super("OOPS!!! The description of a " + cmd + " is invalid.");
    }
}
