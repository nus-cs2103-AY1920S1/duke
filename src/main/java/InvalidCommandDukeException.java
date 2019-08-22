public class InvalidCommandDukeException extends DukeException {
    String command;
    public InvalidCommandDukeException(String command) {
        super(command);
        this.command = command;
    }
    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what '" + this.command +"' means.";
    }
}
