package duke.core;

/**
 * Represents exceptions specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructs a <code>DukeException</code> with a specific string.
     *
     * @param message A string that specifies the problem associated with the
     *              exception.
     */
    DukeException(String message) {
        super(message);
    }
}