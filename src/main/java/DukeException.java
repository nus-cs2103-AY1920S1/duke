public class DukeException extends Exception {

	public DukeException(String... messageLines) {
	    super(append(messageLines));
    }

    private static String append(String... segments) {
		StringBuffer message = new StringBuffer();
		for (String segment : segments) {
			message.append(segment);
		}
		return message.toString();
	}
}
