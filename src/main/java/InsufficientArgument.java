package duke.exception;

/**
 * An exception that is thrown when the command requires more arguments than was
 * provided by the user.
 */
public class InsufficientArgument extends DukeException {

    /**
     * Returns the message that is printed out when the excpetion is met.
     * 
     * @return a string representation that more detail is needed
     */
    public String toString() {
        return "OOPS!!! I need more than this.";
    }
}