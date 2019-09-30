package duke.exception;

public class MissingDoException extends DukeException {
    public String toString() {
        return "OOPS!!! The description of a doAfterTask cannot be empty.";
    }
}
