package duke.exception;

/**
 * An exception that is thrown when the command is not recognized.
 */
public class InvalidCommand extends DukeException {

    /**
     * Returns the message that is printed out when the excpetion is met.
     * 
     * @return a string representation that the command is unclear
     */
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means.";
    }
}