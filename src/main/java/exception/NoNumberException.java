package exception;

/**
 * NoNumberException serves as the specialised error for done and delete tasks without numbers.
 */

public class NoNumberException extends Exception {
    /**
     * Constructor gives the error message based on input from Parser.
     */

    public NoNumberException(String whichTask) {
        super("You didn't tell me which task to " + whichTask + "!!!");
    }
}