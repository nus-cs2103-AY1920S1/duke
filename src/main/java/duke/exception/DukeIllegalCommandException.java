package duke.exception;

/**
 * A {@link DukeException}.
 * <p>
 * Thrown when the user inputs an illegal command.
 */
public class DukeIllegalCommandException extends DukeException {

    public DukeIllegalCommandException(String message) {
        super(message);
    }

}
