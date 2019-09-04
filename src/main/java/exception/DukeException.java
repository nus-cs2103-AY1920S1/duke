package exception;

/**
 * General exception for all Duke related exceptions that other exceptions should extend.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     * @param exceptionMsg Message that will likely be displayed to the user if exception is caught to
     *                     explain what caused the issue
     */
    public DukeException(String exceptionMsg) {
        super(exceptionMsg);
    }
}