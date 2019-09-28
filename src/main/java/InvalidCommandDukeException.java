/**
 * An exception thrown when the parser cannot parse the user input.
 */
public class InvalidCommandDukeException extends DukeException {
    String command;

    /**
     * Constructs the exception.
     *
     * @param command the user input that caused the exception.
     */
    public InvalidCommandDukeException(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Gives out a string that describes the exception.
     *
     * @return A string that describes the exception.
     */
    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what '" + this.command + "' means.";
    }
}
