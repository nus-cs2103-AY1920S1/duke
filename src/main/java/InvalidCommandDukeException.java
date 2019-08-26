/**
 * Represents an exception that is thrown when an invalid command is entered
 * into a Duke object.
 */
public class InvalidCommandDukeException extends DukeException {
    public InvalidCommandDukeException(String message) {
        super(message);
    }
}
