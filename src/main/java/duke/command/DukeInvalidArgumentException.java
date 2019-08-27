package duke.command;

import duke.DukeExceptions;

/**
 * Abstraction of exceptions occuring due to invalid command arguments.
 */
public class DukeInvalidArgumentException extends DukeExceptions {
    /**
     * Constructor that sets only the display message.
     * Defaults to a pre defined error message.
     *
     * @param displayMsg The display message string.
     */
    public DukeInvalidArgumentException(String displayMsg) {
        super("Invalid user arguments to command inputted", displayMsg);
    }

    /**
     * Constructor allowing setting the error and display message.
     *
     * @param errorMsg The informative error message string.
     * @param displayMsg The display message string.
     */
    public DukeInvalidArgumentException(String errorMsg, String displayMsg) {
        super(errorMsg, displayMsg);
    }
}
