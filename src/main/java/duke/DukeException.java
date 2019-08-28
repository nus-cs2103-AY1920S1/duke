package duke;

/**
 * Exception to be thrown when an error occurs in Duke
 */
public class DukeException extends Exception {

    /**
     * Constructs the exception to be thrown when an error occurs
     *
     * @param messageLines The message explaining the error which occured for the exception to be thrown
     */
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
