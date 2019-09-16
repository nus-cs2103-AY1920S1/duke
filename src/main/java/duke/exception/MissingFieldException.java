package duke.exception;

public class MissingFieldException extends DukeException {

    /**
     * Initialises a MissingFieldException.
     *
     * @param missingField The task type entered.
     */
    public MissingFieldException(String missingField) {
        super("Missing a " + missingField + ".");
    }
}
