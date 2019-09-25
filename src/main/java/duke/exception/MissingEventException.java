package duke.exception;

public class MissingEventException extends DukeException {

    public String toString() {
        return "OOPS!!! The description of an event cannot be empty.";
    }

}
