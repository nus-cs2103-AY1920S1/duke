package main;

/**
 * A class to handle all exceptions pertaining to Duke.
 */
public class DukeException extends Exception {
    /**
     * Invokes the superclass Exception.
     */
    public DukeException() {
        super();
    }

    /**
     * Invokes the superclass Exception.
     *
     * @param message The error message to be passed as a param to the superclass
     */
    public DukeException(String message) {
        super(message);
    }
}
