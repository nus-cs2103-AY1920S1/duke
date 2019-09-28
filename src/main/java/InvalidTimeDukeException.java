/**
 * An exception thrown when the given time description does not match the requirements.
 */
public class InvalidTimeDukeException extends DukeException {
    /**
     * Constructs the exception.
     */
    public InvalidTimeDukeException() {
        super("invalid date");
    }

    /**
     * Gives out a string that describes the exception.
     *
     * @return A string that describes the exception.
     */
    @Override
    public String toString() {
        return "Sorry, Dates should be in format 'dd/mm/yyyy hh:mm'";
    }
}
