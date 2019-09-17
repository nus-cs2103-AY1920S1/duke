package duke.exception;

/**
 * This is a runtime exception that occur when there is failed or interrupted operations in the writer.
 */
public class FailedToSaveIoException extends DukeException {

    /**
     * This is the content of the line where the write error occured.
     */
    private String line;

    public FailedToSaveIoException() {
        this.line = "";
    }

    /**
     * Constructs a new line in file write exception with the specified line number and contents where the error
     * occurred.
     * @param line the content of the line where the write error occurred
     */
    public FailedToSaveIoException(String line) {
        super();
        this.line = line;
    }

    /**
     * Gets the content of the line where the write error occurred.
     * @return a string representation of the line content
     */
    public String getLine() {
        return line;
    }

}
