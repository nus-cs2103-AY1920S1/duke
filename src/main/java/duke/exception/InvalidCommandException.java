package duke.exception;

public class InvalidCommandException extends DukeException {

    private String command;

    public InvalidCommandException() {
        super();
    }

    public InvalidCommandException(String command) {
        super();
        this.command = command;
    }

    public String getInvalidCommand() {
        return command;
    }
}
