package duke.exception;

/**
 * Represents a Parser exception that occurs during duke's execution.
 */
public class DukeParserException  extends DukeException {

    /**
     * Constructs a new DukeParserException with the specified error message.
     *
     * @param errorMsg the error message as a String.
     */
    public DukeParserException(String errorMsg) {
        super(errorMsg);
    }
}
