package kappa.exception;

/**
 * Missing date exception, for when date is missing from input.
 */
public class MissingDateException extends KappaException {
    /**
     * Constructor for MissingDateException.
     */
    public MissingDateException() {
        super("Date is missing!");
    }
}
