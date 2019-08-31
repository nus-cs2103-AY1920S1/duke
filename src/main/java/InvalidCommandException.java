public class InvalidCommandException extends DukeException {
    String command;

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
