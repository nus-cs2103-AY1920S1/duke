package duke.exception;

/**
 * An exception that is thrown when the value given by the user is not
 * appropriate.
 */
public class InvalidArgument extends DukeException {
    /**
     * Returns the message that is printed out when the excpetion is met.
     * 
     * @return a string representation that the argument is invalid
     */
    public String toString() {
        return "OOPS!!! Invalid argument";
    }
}
