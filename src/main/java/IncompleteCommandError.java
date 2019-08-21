public class IncompleteCommandError extends UnknownCommandException {
    private String message;
    private String taskName;
    public IncompleteCommandError(String message, String taskName) {
        super(message);
        this.message = message;
        this.taskName = taskName;
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! The description of a " + this.taskName + " cannot be " + this.message + ".";
    }
}
