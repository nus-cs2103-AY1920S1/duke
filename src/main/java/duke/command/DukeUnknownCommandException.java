package duke.command;

import duke.error.DukeException;

/**
 * A DukeException to be thrown when the command of the input given cannot be parsed as a valid command Type for Duke.
 */
public class DukeUnknownCommandException extends DukeException {

    /**
     * Constructs the DukeException to be thrown when the command cannot be parsed as a valid command Type for Duke.
     */
    public DukeUnknownCommandException() {
        super("I'm sorry. I don't know what that means :c");
    }
}
