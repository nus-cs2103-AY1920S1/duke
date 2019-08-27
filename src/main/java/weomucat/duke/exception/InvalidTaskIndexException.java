package weomucat.duke.exception;

/**
 * Thrown when an invalid task index was passed in to TaskList.
 */
public class InvalidTaskIndexException extends DukeException {
	public InvalidTaskIndexException() {
		super("That is not a valid index of a task.");
	}
}
