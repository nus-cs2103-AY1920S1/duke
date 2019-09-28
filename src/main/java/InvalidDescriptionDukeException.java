/**
 * An exception thrown when the description given by the user cannot be used by the command.
 */

public class InvalidDescriptionDukeException extends DukeException {
    String command;
    String description;

    /**
     * Constructs the exception when the following description of a command does not match the requirement.
     *
     * @param command     The command called.
     * @param description The description that was passed by the user for the command.
     */
    public InvalidDescriptionDukeException(String command, String description) {
        super("command:" + command + " description: " + description);
        this.command = command;
        this.description = description;
    }

    /**
     * Gives out a string that describes the exception.
     *
     * @return A string that describes the exception.
     */
    @Override
    public String toString() {
        return "OOPS!!! invalid description '" + this.description + "' for command '" + this.command + "'";
    }

}
