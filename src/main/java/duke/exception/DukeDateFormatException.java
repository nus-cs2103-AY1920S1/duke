package duke.exception;

/**
 * A {@link DukeException}.
 * <p>
 * Thrown when the date in the save file is formatted incorrectly.
 */
public class DukeDateFormatException extends DukeException {

    public DukeDateFormatException(String message) {
        super(message);
    }

}
