package duke.command;

import duke.DukeExceptions;

/**
 * Abstraction of exceptions caused by invalid inputted command types.
 */
class DukeInvalidCommandException extends DukeExceptions {
    /**
     * Constructor that sets only the display message.
     * Defaults to a pre defined error message.
     *
     * @param displayMsg The display message string.
     */
    DukeInvalidCommandException(String displayMsg) {
        super("Invalid user command inputted", displayMsg);
    }
}
