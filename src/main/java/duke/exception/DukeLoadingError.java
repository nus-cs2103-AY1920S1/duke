package duke.exception;

public class DukeLoadingError extends DukeException {
    public String toString() {
        return "OOPS!!! File is empty or not found.  Creating new list...";
    };
}
