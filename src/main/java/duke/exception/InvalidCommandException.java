package duke.exception;

public class InvalidCommandException extends DukeException {

    @Override
    public String getResponseMessage() {
        return "Sorry, I do not understand the command.";
    }

}
