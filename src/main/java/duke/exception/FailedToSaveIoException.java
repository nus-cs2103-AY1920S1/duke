package duke.exception;

/**
 * This is a exception that occur when it fails to save.
 */
public class FailedToSaveIoException extends DukeException {

    /**
     * This is the content of the line where the save error occured.
     */
    private String line;

    /**
     * Constructs a new failed to save exception with a empty line.
     */
    public FailedToSaveIoException() {
        this.line = "";
    }

    /**
     * Constructs a new failed to save exception with the line contents.
     * @param line the content of the line where the save error occurred
     */
    public FailedToSaveIoException(String line) {
        super();
        this.line = line;
    }

    /**
     * Gets the content of the line where the save error occurred.
     * @return a string representation of the line content
     */
    public String getLine() {
        return line;
    }

}
