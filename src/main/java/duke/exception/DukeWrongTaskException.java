package duke.exception;

/**
 * A DukeException subclass that handles errors when description or date of a task is invalid.
 */
public class DukeWrongTaskException extends DukeException {
    /**
     * Constructs the exception to be thrown upon invalid description/date.
     *
     * @param type the type of task with invalid description or date.
     */
    public DukeWrongTaskException(String type) {
        super(String.format("OOPS!!! The description or timing of %s is problematic.", type));
    }
}
