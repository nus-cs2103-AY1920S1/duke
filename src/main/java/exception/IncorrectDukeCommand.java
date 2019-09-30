package exception;

/**
 * Represents an exception that reflects a valid input command but with unrecognizable contents.
 */
public class IncorrectDukeCommand extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public IncorrectDukeCommand(String message) {
        super(message);
    }
}
