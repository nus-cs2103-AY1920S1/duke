package duke.exception;

/**
 * An exception that is thrown when the a preposition is required but is not
 * given.
 */
public class MissingPreposition extends DukeException {

    private String command;

    /**
     * Creates a new exception that the command requires a preposition. Takes in the
     * command that was used so that the user is clear that this command requires a
     * preposition
     * 
     * @param command the command that was entered by the user
     */
    public MissingPreposition(String command) {
        this.command = command;
    }

    /**
     * Returns the message that is printed out when the excpetion is met.
     * 
     * @return a string representation that the command requires a preposition
     */
    public String toString() {
        return String.format("Oops!! %s requires a preposition for the time.", command);
    }
}