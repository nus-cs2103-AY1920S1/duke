package slave.exception;

/**
 * Represents an invalid date exception.
 */
public class InvalidDateException extends KappaException {

    /**
     * Constructor for invalid date.
     */
    public InvalidDateException() {
        super("Date is invalid!");
    }
}
