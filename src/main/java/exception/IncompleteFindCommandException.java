package exception;

/**
 * IncompleteFindCommandException serves as the specialised error for
 * any find tasks that are input in the wrong format
 */

public class IncompleteFindCommandException extends Exception {
    /**
     * Constructor giver the error message based on each task from Parser
     */

    public IncompleteFindCommandException() {
        super("You didn't tell me what to find!!!");
    }
}