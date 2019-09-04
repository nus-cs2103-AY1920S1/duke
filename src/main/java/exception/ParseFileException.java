package exception;

/**
 * Extends DukeException and thrown when there is an error reading contents of save .txt file into TaskList.
 */
public class ParseFileException extends DukeException {

    /**
     * Constructor for ParseFileException.
     * @param exceptionMsg informs the user that an error occurred while reading the contents of the
     *                     state file or while trying to write contents to TaskList
     */
    public ParseFileException(String exceptionMsg) {
        super(exceptionMsg);
    }
}