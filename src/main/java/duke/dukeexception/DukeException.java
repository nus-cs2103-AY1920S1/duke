package duke.dukeexception;

/**
 * Class that handles the exceptions thrown by the Duke program.
 */
public class DukeException extends Exception {
    /**
     * Class constructor that specifies the exception message associated with
     * this instance.
     *
     * @param message Error message to be printed.
     */
    public DukeException(String message){
        super(message);
    }
}
