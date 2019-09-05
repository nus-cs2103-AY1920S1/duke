package duke.command;

import duke.exception.DukeException;

/**
 * Represents an exceptional situation that deals with an invalid find command.
 */
public class InvalidFindDukeException extends DukeException {

    public InvalidFindDukeException(String message) {
        super(message);
    }

}
