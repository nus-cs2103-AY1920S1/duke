package duke.io;

import duke.error.DukeException;

/**
 * The DukeException to be thrown when a Duke TaskList save file cannot be loaded.
 */
public class DukeInvalidLoadFilePathException extends DukeException {

    /**
     * Constructs DukeException to be thrown when a Duke TaskList save file cannot be loaded.
     *
     * @param path The file path of the save file which cannot bo loaded.
     */
    DukeInvalidLoadFilePathException(String path) {
        super("Your task list could not be loaded from " + path);
        assert path != null;
    }
}