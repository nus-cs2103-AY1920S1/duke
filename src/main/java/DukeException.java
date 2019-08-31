public class DukeException extends Exception {

	protected String inputMessage;

	public DukeException(String inputMessage) {

		this.inputMessage = inputMessage;
	}

	@Override
	public String toString() {

		return this.inputMessage;
	}
}
