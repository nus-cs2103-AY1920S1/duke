/**
 * To handle several logic error from the program that is thrown to this exception.
 *
 * @param message the details that the developer used to notify the users of an error.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }
}