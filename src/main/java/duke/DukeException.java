package duke;

/**
 * Represents the exception that can store an error message
 * to be printed for the user in the operation of Duke.
 */
public class DukeException extends Exception {

    /**
     * Creates an instance of a DukeException.
     * @param message indicates more details about the exception
     */
    public DukeException(String message) {
        super("☹ OOPS!!! " + message);
    }
}
