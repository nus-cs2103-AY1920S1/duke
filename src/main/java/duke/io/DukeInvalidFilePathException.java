package duke.io;

import duke.error.DukeException;

/**
 * The DukeException to be thrown when the file at some file path is missing, or is a directory.
 */
public class DukeInvalidFilePathException extends DukeException {

    /**
     * Constructs DukeException to be thrown when the file at some file path is missing, or is a directory.
     *
     * @param path The file path of the file which is missing, or is a directory
     */
    public DukeInvalidFilePathException(String path) {
        super("Your task list cannot be saved/loaded because the following is not a valid file path:\n",
                path);
    }
}