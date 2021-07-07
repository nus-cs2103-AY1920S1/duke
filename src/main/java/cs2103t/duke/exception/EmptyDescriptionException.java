package cs2103t.duke.exception;

/**
 * Represents exceptions where no description is given after keywords todo, deadline or event.
 */
public class EmptyDescriptionException extends DukeException {
    /** Keyword that caused this exception. */
    private String type;

    /**
     * Constructs EmptyDescriptionException.
     * @param msg keyword of task command.
     */
    public EmptyDescriptionException(String msg) {
        super(msg);
        this.type = msg;
    }

    @Override
    public String toString() {
        return String.format("\u2639 OOPS!!! The description of %s cannot be empty.",
                this.type);
    }
}
