package duke;

/**
 * A custom Duke exception to handle errors specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Instantiates a custom Duke exception.
     * @param error The exception's error message.
     */
    public DukeException(String error) {
        super(error);
    }
}
