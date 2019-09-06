package duke.command;

public class EmptyHistoryException extends UnknownCommandException {
    public EmptyHistoryException() {
        super(null);
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! No more commands to undo.";
    }
}
