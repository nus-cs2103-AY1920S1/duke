package duke.exception;

/**
 * A {@link DukeException}.
 * This is the java {@link java.io.IOException} wrapped into a DukeException.
 * Additionally, this exception is thrown when the save file is formatted incorrectly.
 */
public class DukeIOException extends DukeException {

    public DukeIOException(String message) {
        super(message);
    }

}
