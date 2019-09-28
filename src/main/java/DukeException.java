/**
 * Creates a DukeException as a subclass of Exception to
 * handle certain edge cases.
 */
public class DukeException extends Exception {
	protected String inputMessage;

	public DukeException(String inputMessage) {
		this.inputMessage = inputMessage;
	}

	/**
	 * Returns the exception message.
	 *
	 * @return String of the exception message.
	 */
	@Override
	public String toString() {
		return this.inputMessage;
	}
}
