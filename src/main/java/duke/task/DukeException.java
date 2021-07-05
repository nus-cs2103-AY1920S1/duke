package duke.task;

/**
 * The DukeException class defines the behaviour of a DukeException.
 * 
 * @author Joel Loong
 */
public class DukeException extends IllegalArgumentException {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}