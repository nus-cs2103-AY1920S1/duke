package duke.exception;

public class DukeException extends Exception {

    /**
     * Initialises a DukeException.
     *
     * @param message The error message
     */
    public DukeException(String message) {
        super("☹ OOPS!!! " + message);
    }

}
