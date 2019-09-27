package exception;

/**
 * IncompleteFindCommandException serves as the specialised error for any find tasks with missing user input.
 */

public class IncompleteFindCommandException extends Exception {
    /**
     * Constructor gives the error message based on each task from Parser.
     */

    public IncompleteFindCommandException() {
        super("You didn't tell me what to find!!!");
    }
}