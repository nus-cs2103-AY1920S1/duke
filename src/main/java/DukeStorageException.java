/**
 * Thrown when data in text file in wrong format.
 */
public class DukeStorageException extends DukeException {
    public DukeStorageException() {
        super("Whoops! Please check that the task types in the text file are only from this list [T, D, E].");
    }
}