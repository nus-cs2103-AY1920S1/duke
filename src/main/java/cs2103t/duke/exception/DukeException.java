package cs2103t.duke.exception;

/**
 * Represents all exceptions that will be encountered in the Duke chatbot.
 * This can be thrown when input formats and keywords are incorrect, correct format but invalid arguments,
 * or when the file cannot be opened properly.
 */
public class DukeException extends IllegalArgumentException {
    /**
     * Constructs a DukeException.
     * Parent class IllegalArgumentException is initialized.
     * @param msg error message.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
