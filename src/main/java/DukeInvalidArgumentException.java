/**
 * Creates a DukeInvalidArgumentException which extends
 * DukeException to handle invalid arguments.
 */
public class DukeInvalidArgumentException extends DukeException {
	private String inputMessage;

	public DukeInvalidArgumentException(String message, String inputMessage) {
		super(message);
		this.inputMessage = inputMessage;
	}


	/**
	 * Returns the exception message together with the
	 * input the user has entered.
	 *
	 * @return String of error message and input by user.
	 */
	@Override
	public String toString() {
		return super.toString() + "\nInput by user: " + inputMessage;
	}
}
