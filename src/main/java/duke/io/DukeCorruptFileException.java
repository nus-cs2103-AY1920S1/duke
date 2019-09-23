package duke.io;

import duke.error.DukeException;

import java.io.File;

/**
 * The DukeException to be thrown when a the file at the given file path is not formatted
 * in the way the method reading the file expects it to be formatted.
 */
public class DukeCorruptFileException extends DukeException {

    /**
     * Constructs the DukeException thrown when a file is not formatted in the way the method that
     * throws this expects.
     *
     * @param file The file path of the badly formatted file
     */
    DukeCorruptFileException(File file) {
        super("The save file at ", file.getAbsolutePath(), " is formatted incorrectly.");
        assert file.isFile();
    }
}