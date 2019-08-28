public class DukeException extends Exception {
	/**
	 * Constructor.
	 * @param message Exception message.
	 */
	public DukeException(String message) {
		super(message);
	}
	
	/**
	 * Removes "DukeException: " from start of message.
	 */
	@Override public String toString() {
		String temp = super.toString();
		return temp.substring(15);
	}
}