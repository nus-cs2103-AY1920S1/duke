package duke;

/**
 * Specifies duke-defined exceptions for invalid commands.
 */
public class DukeException extends Exception{
    private String message = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * Constructs DukeException object for invalid commands.
     */
    DukeException() {}


    /**
     * Constructs DukeException object for insufficient information for specified commands
     * @param message Specified command.
     */
    DukeException(String message) {
        this.message = "OOPS!!! The description of a " + message + " cannot be empty.";
    }

    /**
     * Returns the command that is causing an error.
     * @return Specified command.
     */
    public String getMessage() {
        return message;
    }
}
