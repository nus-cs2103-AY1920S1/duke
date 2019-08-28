package duke.command;

import duke.DukeException;

/**
 * Exception to be thrown when no command is given when one is expected
 */
public class DukeMissingCommandException extends DukeException {

    /**
     * Constructs the exception to be thrown when to command is given
     */
    public DukeMissingCommandException() {
        super("No command?! I didn't w-want to do anything anyway!");
    }
}
