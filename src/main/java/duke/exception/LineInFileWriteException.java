package duke.exception;

/**
 * This is a runtime exception that occur when there is failed or interrupted operations in the writer.
 */
public class LineInFileWriteException extends DukeException {

    /**
     * This is the line number that the write error occcured.
     */
    private static int lineCount = 0;

    /**
     * This is the content of the line where the write error occured.
     */
    private String line;

    /**
     * Constructs a new line in file write exception with the specified line number and contents where the error
     * occurred.
     * @param line the content of the line where the write error occurred
     */
    public LineInFileWriteException(String line) {
        super();
        this.lineCount++;
        this.line = line;
    }

    /**
     * Gets the content of the line where the write error occurred.
     * @return a string representation of the line content
     */
    public String getLine() {
        return line;
    }

    /**
     * Gets the line number where write error occurred.
     * @return the line number where erite error occurred
     */
    public int getLineCount() {
        return lineCount;
    }

}
