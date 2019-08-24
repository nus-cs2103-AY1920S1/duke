/**
 * Encapsulates exceptions that are for the Duke Task manager.
 * It handles user's input error. The class provides extenstion to other Duke specific exception classes.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException object.
     * @param message This contains the message of the error.
     *                The message of the error will be shown to the user when thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}

/**
 * Encapsulates exception that will be thrown when Todo description is empty.
 */
class EmptyToDoDescriptionException extends DukeException {
    /**
     * Constructs a new exception that takes in a message.
     * This message should be specific to warn the user that Todo description should not be empty.
     * @param message This is the message that will be displayed users to advise them to add a description.
     */
    public EmptyToDoDescriptionException(String message) {
        super(message);
    }
}

/**
 * Encapsulates exception that will be thrown when the command from the user
 * is unregistered / unknown to the bot.
 */
class UnknownCommandException extends DukeException {
    /**
     * Constructs a new exception that takes in a message.
     * This message should be specific to warn the user that the command is unknown to the bot.
     * A registered command should be used instead.
     * @param message This is the message that will be displayed users to advise them to use an appropriate command.
     */
    public UnknownCommandException(String message) {
        super(message);
    }
}

/**
 * Encapsulates exception that will be thrown when the description is empty.
 */
class EmptyDescriptionException extends DukeException {
    /**
     * Constructs a new exception that takes in a message.
     * This message should be specific to warn the user that description should not be empty.
     * @param message This is the message that will be displayed users to advise them to add a description.
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}

/**
 * Encapsulates exception that will be thrown when the due date is empty.
 */
class EmptyDueDateException extends DukeException {
    /**
     * Constructs a new exception that takes in a message.
     * This message should be specific to warn the user that a due date should be specified.
     * @param message This is the message that will be displayed users to advise them to add a due date.
     */
    public EmptyDueDateException(String message) {
        super(message);
    }
}

/**
 * Encapsulates exception that will be thrown when the index specified by the user
 * is not within the perimeter of the list.
 */
class IndexDoesNotExistException extends DukeException {
    /**
     * Constructs a new exception that takes in a message.
     * This message should be specific to warn the user that the index used is not within the list.
     * @param message This is the message that will be displayed users to advise them to use an
     *                index that is within the perimeter of the list.
     */
    public IndexDoesNotExistException(String message) {
        super(message);
    }
}