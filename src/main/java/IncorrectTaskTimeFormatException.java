/**
 * Thrown if the user enters a DeadlineTask or EventTask with an incorrect time format.
 */
public class IncorrectTaskTimeFormatException extends DukeException {
    /**
     * Creates an IncorrectTaskTimeFormatException exception.
     *
     * @param message Message to be printed.
     */
    public IncorrectTaskTimeFormatException(String message) {
        super(message);
    }
}
