package duke;

/**
 * Represents a DukeException object. A <code>DukeException</code> object corresponds to
 * an exception to be thrown in Duke.
 */
public class DukeException extends Exception {

    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

