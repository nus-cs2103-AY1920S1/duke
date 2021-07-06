package duke.command.exceptions;

import duke.exception.DukeException;

/**
 * Represents an exceptional situation involving invalid commands.
 */
public class InvalidCommandDukeException extends DukeException {

    public InvalidCommandDukeException(String message) {
        super(message);
    }

}
