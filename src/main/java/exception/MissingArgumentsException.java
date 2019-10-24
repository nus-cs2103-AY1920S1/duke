package exception;

public class MissingArgumentsException extends DukeException {
    private String command;

    public MissingArgumentsException(String command) {
        this.command = command;
    }

    @Override
    public String getMessage() {
        return String.format("%s The description of %s cannot be empty.", super.toString(), command);
    }
}
