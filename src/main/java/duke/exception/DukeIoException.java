package duke.exception;

/**
 * A {@link DukeException}.
 *
 * <p>This is the {@link java.io.IOException} wrapped into a DukeException.
 * Additionally, this exception is thrown when the save file is formatted incorrectly.
 */
public class DukeIoException extends DukeException {

    public DukeIoException(String message) {
        super(message);
    }

}
