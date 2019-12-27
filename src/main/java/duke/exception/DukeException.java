package duke.exception;

/**
 * Represents exception arisen when running the application.
 */
public class DukeException extends Exception{

    /**
     * Generates an exception with customised message to be shown.
     * @param message Message to be printed.
     */
    public DukeException(String message) {
        super(message);
    }

}
