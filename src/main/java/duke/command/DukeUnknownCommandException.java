package duke.command;

import duke.error.DukeException;

/**
 * A DukeException to be thrown when the user's input cannot be parsed as a valid Command for Duke.
 */
public class DukeUnknownCommandException extends DukeException {

    /**
     * Constructs the DukeException to be thrown when the user's input cannot be parsed as a valid Command for Duke.
     */
    public DukeUnknownCommandException() {
        super("I'm sorry. I don't know what that means :c");
    }
}
