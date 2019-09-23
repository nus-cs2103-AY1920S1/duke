package duke.io;

import duke.error.DukeException;

/**
 * The DukeException to be thrown when a Duke TaskList save file cannot be loaded.
 */
public class DukeInvalidSaveFilePathException extends DukeException {

    /**
     * Constructs DukeException to be thrown when a Duke TaskList save file cannot be loaded.
     *
     * @param path The file path of the save file which cannot bo loaded.
     */
    DukeInvalidSaveFilePathException(String path) {
        super("Changes to your task list cannot be saved to " + path);
        assert path != null;
    }
}