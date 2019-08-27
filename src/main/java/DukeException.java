public class DukeException extends Exception {

    private String message;
    public DukeException(String message) {
        super("OOPS! " + message);
        this.message = message;
    }

    public String getMessage() { return this.message; }
}

//Empty description after command (todo, event, deadline, list, done)
class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException (String command) {
        super("The description of " + command +
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

// When no existing data in hard disk
class DukeDataException extends DukeException {
    public DukeDataException() {
        super("Whoops! No data in hard disk yet.");
    }
}