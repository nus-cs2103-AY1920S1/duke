package duke.exception;

/**
 * This is a runtime exception that occur when the file is parsed line by line.
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
     * Constructs a new line in file parse exception with the specified line number and contents where the error
     * occurred.
     * @param lineCount the line number where the parsing error occurred
     * @param line the content of the line where the parsing error occurred
     */
    public FailedToLoadIoException(int lineCount, String line) {
        super();
        this.lineCount = lineCount;
        this.line = line;
    }

    /**
     * Gets the content of the line where the parsing error occurred.
     * @return a string representation of the line content
     */
    public String getLine() {
        return line;
    }

    /**
     * Gets the line number where parsing error occurred.
     * @return the line number where parsing error occurred
     */
    public int getLineCount() {
        return lineCount;
    }

}
