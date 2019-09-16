package exception;

public class InvalidCommandException extends DukeException {
    @Override
    public String getMessage() {
        return String.format("%s I'm sorry, but I don't know what that means :-(", super.toString());
    }
}
