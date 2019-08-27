package duke.storage;

import duke.DukeExceptions;

/**
 * Abstraction of exceptions due to a failure to write task data to disk.
 */
public class DukeFileWriteException extends DukeExceptions {
    /**
     * Constructor that sets only the display message.
     * Defaults to a pre defined error message.
     *
     * @param displayMsg The display message string.
     */
    DukeFileWriteException(String displayMsg) {
        super("Error encountered while writing task data to disk", displayMsg);
    }

    /**
     * Constructor allowing setting the error and display message.
     *
     * @param errorMsg The informative error message string.
     * @param displayMsg The display message string.
     */
    DukeFileWriteException(String errorMsg, String displayMsg) {
        super(errorMsg, displayMsg);
    }
}
