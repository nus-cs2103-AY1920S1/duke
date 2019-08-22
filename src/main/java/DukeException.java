public class DukeException extends Exception {
    public DukeException(String message) {
        super("OOPS! " + message);
    }
}

//Empty description after command (todo, event, deadline, list, done)
class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException (String command) {
        super("The description of a " + command +
                " cannot be empty.");
    }
}

//Unrecognised first word command
class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}

//Incorrect usage of 'deadline' or 'event'
//Commands requiring additional info of "/by" or "/at" (either absent or occuring more than once)
class IncorrectInfoInputException extends DukeException {
    public IncorrectInfoInputException(String subCommand) {
        super("Please have one \"" + subCommand + "\" provided." );
    }
}