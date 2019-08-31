package cs2103t.duke.exception;

/**
 * Represents exceptions where deadline or event command does not have the correct format.
 */
public class IncorrectTaskFormatException extends DukeException {
    /** The keyword that indicates next argument is date/time. */
    private String keyword2;

    /**
     * Constructs IncorrectTaskFormatException.
     * @param msg by or at (for deadline and event respectively).
     */
    public IncorrectTaskFormatException(String msg) {
        super(msg);
        this.keyword2 = msg;
    }

    @Override
    public String toString() {
        return String.format("\u2639 Aish try again with the correct format: <descr> /%s <dateinfo>",
                this.keyword2);
    }
}
