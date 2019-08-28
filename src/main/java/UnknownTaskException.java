/**
 * An exception that occurs when Duke is unable to decipher which command is given.
 */

public class UnknownTaskException extends DukeException {
    public UnknownTaskException(String message) {
        super(message);
    }
}
