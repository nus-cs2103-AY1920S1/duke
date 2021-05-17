package exception;

/**
 * Exception where the user enters a wrong date format instead of dd/mm/yyyy hhmm.
 */
public class WrongDateFormatException extends DukeException {
    public WrongDateFormatException(String message) {
        super(message);
    }
}
