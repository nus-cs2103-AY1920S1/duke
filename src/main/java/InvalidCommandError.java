public class InvalidCommandError extends UnknownCommandException {
    private String message;
    public InvalidCommandError(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! The statement: \"" + this.message + "\" is invalid. ";
    }
}
