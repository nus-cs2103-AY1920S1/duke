package weomucat.duke.exception;

/**
 * An exception that occurred within Duke.
 */
public class DukeException extends Exception {
	/**
	 * @param message error message to display
	 */
	public DukeException(String message) {
		super(message);
	}
}
