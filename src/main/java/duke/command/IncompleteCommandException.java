package duke.command;

public class IncompleteCommandException extends UnknownCommandException {
    private String message;
    private String taskName;

    /**
     * Constructor.
     * @param message - Message describing command: Empty or incomplete
     * @param taskName - Name of task
     */
    public IncompleteCommandException(String message, String taskName) {
        super(message);
        this.message = message;
        this.taskName = taskName;
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! The description of a " + this.taskName + " cannot be " + this.message + ".";
    }
}
