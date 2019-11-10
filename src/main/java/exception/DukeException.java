package exception;

/**
 * Class that represents an exception thrown when Duke encounters an invalid command.
 */
public class DukeException extends Exception {

    public DukeException(String msg) {
        super(msg);
    }
}
