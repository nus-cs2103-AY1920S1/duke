package duke.command.exception;

import duke.exception.DukeException;

/**
 * Encapsulate the exception thrown when task index entered is not an integer.
 */
public class IllegalTaskIndexException extends DukeException {

    public IllegalTaskIndexException(String msg) {
        super(msg);
    }
}
