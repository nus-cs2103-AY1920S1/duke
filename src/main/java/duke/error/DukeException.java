package duke.error;

/**
 * A DukeException is an Exception which can be thrown when an error occurs in Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructs the DukeException to be thrown when an error occurs.
     *
     * @param messageLines The message explaining the error which caused the DukeException to be thrown
     */
    public DukeException(String... messageLines) {
        super(append(messageLines));
    }

    /**
     * Helper method to append the varargs String provided together into one string.
     *
     * @param segments The different Strings to be appended.
     * @return The combined String.
     */
    private static String append(String... segments) {
        StringBuilder message = new StringBuilder();
        for (String segment : segments) {
            message.append(segment);
        }
        return message.toString();
    }
}
