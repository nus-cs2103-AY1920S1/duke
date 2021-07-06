package duke.date;

import duke.exception.DukeException;

/**
 * Represents an exceptional situation related to invalid date formats and semantics.
 */
public class InvalidDateDukeException extends DukeException {

    /**
     * Constructor of the exception.
     * @param message Message carried by the exception.
     */
    public InvalidDateDukeException(String message) {
        super(message);
    }

}
