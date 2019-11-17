package duke.exception;

/**
 * This is a exception that occur when the user enters an invalid command.
 */
public class InvalidCommandException extends DukeException {

    /**
     * This is the command entered by the user.
     */
    private String command;

    /**
     * Constructs a new invalid command exception where the parameter is <code>null</code>.
     */
    public InvalidCommandException() {
        super();
    }

    /**
     * Constructs a new invalid parameter exception with a specified command entered by the user.
     * @param command the command entered by the user which is invalid
     */
    public InvalidCommandException(String command) {
        super();
        this.command = command;
    }

    /**
     * Gets the invalid command that is entered by the user.
     * @return the invalid command
     */
    public String getInvalidCommand() {
        return command;
    }

}
