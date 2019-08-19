package duke.error;

import java.lang.Exception;

public abstract class DukeException extends Exception {
    /**
     * Constructor.
     * @param message String
     */
    public DukeException(String message) {
        super(message);
    }
}
