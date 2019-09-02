/**
 * Thrown when no such txt file (data) found in hard disk.
 */
public class DukeDataException extends DukeException {
    public DukeDataException() {
        super("Whoops! No data in hard disk yet.");
    }
}