/**
 * Represents a custom exceptions class that extends from DukeException.
 * This exception is thrown when there are missing inputs such as the deadline or event time/day.
 */
public class MissingInputException extends DukeException {

    public MissingInputException(String message) {
        super(message);
    }

}
