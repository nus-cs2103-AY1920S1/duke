package duke.command;

import duke.exception.DukeException;

/**
 * Represents an exceptional situation dealing with an invalid delete command.
 */
public class InvalidDeleteDukeException extends DukeException {

    public InvalidDeleteDukeException(String message) {
        super(message);
    }

}
