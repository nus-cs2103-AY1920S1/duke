package Exceptions;

public class InvalidCommandException extends DukeException {

    private String message = "☹ OOPS!!! ";

    /**
     * Creates InvalidCommandException when commands that are not understood by program are given.
     */
    public InvalidCommandException() {}

    /**
     * Creates String for error message.
     * @return error message.
     */
    @Override
    String errorMessage() {
        this.message += "I'm sorry, but I don't know what that means :-(";
        return this.message;
    }
}
