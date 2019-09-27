package exception;

/**
 * DukeException serves as the main class for any incorrect user input.
 */

public class DukeException extends Exception {

    /**
     * Constructor giver the error message.
     */

    public DukeException() {

        super("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-( ");
    }
}

