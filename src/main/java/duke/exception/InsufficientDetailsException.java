package duke.exception;

import duke.exception.DukeException;

public class InsufficientDetailsException extends DukeException {

    /**
     * Constructor for <code>InsufficientDetailsException</code>.
     *
     * @param message Message about the exception.
     */
    public InsufficientDetailsException(String message) {
        super(message);
    }
}
