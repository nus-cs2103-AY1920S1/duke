package kappa.exception;

/**
 * Customised Exception for this particular program.
 */
public abstract class KappaException extends Exception {

    /**
     * Constructor for abstract class DukeException.
     *
     * @param message Error message.
     */
    public KappaException(String message) {
        super(message + " Kappa is sad.");
    }

}