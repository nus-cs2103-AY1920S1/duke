public class DukeInvalidArgumentException extends DukeException {

	protected String inputMessage;

	public DukeInvalidArgumentException (String message, String inputMessage) {

		super(message);
		this.inputMessage = inputMessage;
	}

	@Override
	public String toString() {

		return super.toString() + "\nInput by user: " + inputMessage;
	}
}
