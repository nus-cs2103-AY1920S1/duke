package duke.exception;

/**
 * Represents the Loading Error when there is no data or file to be loaded by the storage.
 */
public class DukeLoadingError extends DukeException {
    public String toString() {
        return "OOPS!!! File is empty or not found.  Creating new list...";
    }
}
