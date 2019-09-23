package duke.exception;

/**
 * Represents an exception when Task is created without input.
 */
public class EmptyTaskDukeException extends DukeException {
	public EmptyTaskDukeException(String message) {
		super(message);
	}
}
