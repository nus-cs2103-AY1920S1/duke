package duke.io;

import duke.error.DukeException;

/**
 * The DukeException to be thrown when the file name used for a save file contains non-alphanumeric
 * characters.
 */
public class DukeInvalidFileNameException extends DukeException {

    /**
     * Constructs the DukeException thrown when a file is not formatted in the way the method that
     * throws this expects.
     *
     * @param fileName The file path of the badly formatted file
     */
    DukeInvalidFileNameException(String fileName) {
        super(fileName + " is not a valid Duke save file name. Duke save file names should only "
                + "consist of alphanumeric characters.");
    }
}