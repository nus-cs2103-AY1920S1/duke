package duke.exception;

/**
 * A duke exception is an exception thrown during the execution of the Duke application.
 */
public class DukeException extends Exception {
    String errorMsg;

    /**
     * Constructs a new DukeException that contains an error message.
     *
     * @param errorMsg the error message that represents the error.
     */
    public DukeException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return String.format("☹ OOPS!!! %s", this.errorMsg);
    }
}
