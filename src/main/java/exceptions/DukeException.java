package exceptions;

public class DukeException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Create an exception specific to Duke.
     *
     * @param message a description of the problem
     */
    public DukeException(String message) {
        super(message);
    }
}