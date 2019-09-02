package duke.exception;

/**
 * A {@link DukeException}.
 * <p>
 * Thrown when an illegal statement is inputted by the user.
 */
public class DukeIllegalArgumentException extends DukeException {

    public DukeIllegalArgumentException(String message) {
        super(message);
    }

}
