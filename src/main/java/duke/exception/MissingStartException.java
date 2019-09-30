package duke.exception;

public class MissingStartException extends DukeException {
    public String toString() {
        return "OOPS!!! Please provide a start date and time.";
    }
}
