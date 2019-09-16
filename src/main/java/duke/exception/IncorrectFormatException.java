package duke.exception;

public class IncorrectFormatException extends DukeException {

    public IncorrectFormatException() {
        super("Incorrect format detected. Please try again.");
    }

    /**
     * Initialises a IncorrectFormatException.
     *
     * @param command The command entered.
     * @param reason The specific reason why it is an incorrect format.
     */
    public IncorrectFormatException(String command, String reason) {
        super("Incorrect format detected. Please try again.\n" + command + " " + reason);
    }

    /**
     * Initialises a IncorrectFormatException.
     *
     * @param command The command used in the wrong format.
     */
    public IncorrectFormatException(String command) {
        super("Incorrect format detected. Please try again with the following format...\n" + getCorrectFormat(command));
    }
}
