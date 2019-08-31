package duke.exception;

public class DukeWrongTaskException extends DukeException {
    public DukeWrongTaskException(String type) {
        super(String.format("☹ OOPS!!! The description of %s is problematic.", type));
    }
}
