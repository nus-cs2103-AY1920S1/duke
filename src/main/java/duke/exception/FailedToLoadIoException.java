package duke.exception;

/**
 * This is a exception that occur when the file is parsed line by line.
 */
public class FailedToLoadIoException extends DukeException {

    /**
     * This is the line number that the parsing error occurred.
     */
    private int lineCount;

    /**
     * This is the content of the line where the parsing error occurred.
     */
    private String line;

    /**
     * Constructs a new failed to load exception with the specified line number and contents where the error
     * occurred.
     * @param lineCount the line number where it failed to load
     * @param line the content of the line where it failed to load
     */
    public FailedToLoadIoException(int lineCount, String line) {
        super();
        this.lineCount = lineCount;
        this.line = line;
    }

    /**
     * Gets the content of the line where the error occured.
     * @return a string representation of the line content
     */
    public String getLine() {
        return line;
    }

    /**
     * Gets the line number where the error occurred.
     * @return the line number where the error occured
     */
    public int getLineCount() {
        return lineCount;
    }

}
