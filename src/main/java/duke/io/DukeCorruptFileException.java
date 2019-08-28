package duke.io;

import duke.DukeException;

/**
 * Exception to be thrown when a the file at the given file path is not formatted in the way the method reading the
 * file expects it to be formatted.
 */
public class DukeCorruptFileException extends DukeException {

    /**
     * Constructs the exception thrown when a file is not formatted in the way the method reading the file expects
     *
     * @param filePath The file path of the badly formatted file
     */
    public DukeCorruptFileException(String filePath) {
        super(new StringBuffer("The file at ").append(filePath)
                .append(" is formatted incorrectly.")
                .toString());
    }
}