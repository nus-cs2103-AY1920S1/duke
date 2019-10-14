package duke.exception;

/**
 * An exception that is thrown when the description is invalid.
 */
public class InvalidDescription extends DukeException {
    private String command;

    /**
     * Creates a new exception that the description is invalid. Takes in the command
     * that was used so that the user is clear that this command requires a
     * description
     * 
     * @param command the command that was entered by the user
     */
    public InvalidDescription(String command) {
        this.command = command;
    }

    /**
     * Returns the message that is printed out when the excpetion is met.
     * 
     * @return a string representation that the description cannot be empty
     */
    public String toString() {
        return String.format("OOPS!!! The description of a %s cannot be empty.", command);
    }
}