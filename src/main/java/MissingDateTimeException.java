/**
 * Indicates missing date and time of the task.
 */
public class MissingDateTimeException extends DukeException {
    /**
     * Creates an exception with the given error message.
     * @param errorMessage that describes the issue with Duke.
     */
    public MissingDateTimeException(String errorMessage) {
        super(errorMessage);
    }
}