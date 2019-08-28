public class DukeException extends Exception {

    private String message;
    public DukeException(String message) {
        super("OOPS! " + message);
        this.message = message;
    }

    public String getMessage() { return this.message; }
}

//Empty description after command (todo, event, deadline, list, done)

/**
 * Thrown if Empty description after command
 * (e.g. todo, event, deadline, list, done)
 */
class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException (String command) {
        super("The description of " + command +
                " cannot be empty.");
    }
}

/**
 * Thrown when user input (first word) is unrecognised/not in program
 */
class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}

/**
 * Thrown when incorrect usage of commands with subcommands
 * e.g. deadline with /by, event with /at
 * These commands require additional info of subcommands
 * These info can either be absent or more than one subcommand was entered
 */
class IncorrectInfoInputException extends DukeException {
    public IncorrectInfoInputException(String subCommand) {
        super("Please have one \"" + subCommand + "\" provided." );
    }
}

/**
 * Thrown when no such txt file (data) found in hard disk
 */
class DukeDataException extends DukeException {
    public DukeDataException() {
        super("Whoops! No data in hard disk yet.");
    }
}