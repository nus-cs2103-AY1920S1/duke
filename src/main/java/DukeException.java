public class DukeException extends Exception {
	public DukeException(String message) {
		super(message);
	}
	
	@Override public String toString() {
		String temp = super.toString();
		return temp.substring(15);
	}
}