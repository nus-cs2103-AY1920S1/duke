package duke.exception;

/**
 * Represents an error in the program. A <code>DukeException</code> object corresponds to
 * an error while running the program, such as missing files and poor user input.
 */
public class DukeException extends Exception {
    private String errorMsg;

    public DukeException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public String toString() {
        return errorMsg;
    }
}
