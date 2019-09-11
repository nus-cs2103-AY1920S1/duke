package duke.command;

public class UnknownCommandException extends Exception {

    /**
     * Constructor.
     * @param message - unknown command given by user
     */
    public UnknownCommandException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
