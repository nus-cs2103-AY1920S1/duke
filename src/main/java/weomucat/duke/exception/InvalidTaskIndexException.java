package weomucat.duke.exception;

public class InvalidTaskIndexException extends DukeException {
	public InvalidTaskIndexException() {
		super("That is not a valid index of a task.");
	}
}
