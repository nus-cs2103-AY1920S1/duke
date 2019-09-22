package exceptions;

public class InvalidCommandException extends DukeException {

    /**
     * Creates InvalidCommandException when commands that are not understood by program are given.
     */
    public InvalidCommandException() {
        this.errorMessage += "I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Creates String for error message.
     *
     * @return error message.
     */
    @Override
    public String getErrorMessage() {
        return msg.getLinedMessage(this.errorMessage);
    }
}
