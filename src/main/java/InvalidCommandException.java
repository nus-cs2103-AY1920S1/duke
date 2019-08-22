public class InvalidCommandException extends DukeException {

    public InvalidCommandException() {}

    @Override
    public String errorMessage() {
        message += "I'm sorry, but I don't know what that means :-(";
        return message;
    }
}
