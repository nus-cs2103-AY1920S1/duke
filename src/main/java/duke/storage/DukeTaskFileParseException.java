package duke.storage;

import duke.DukeExceptions;

/**
 * Abstraction of exceptions caused by errors parsing the existing task file on disk.
 */
class DukeTaskFileParseException extends DukeExceptions {
    /**
     * Constructor that sets only the display message.
     * Defaults to a pre defined error message.
     *
     * @param displayMsg The display message string.
     */
    public DukeTaskFileParseException(String displayMsg) {
        super("Error encountered while parsing task data file", displayMsg);
    }

    /**
     * Constructor allowing setting the error and display message.
     *
     * @param errorMsg The informative error message string.
     * @param displayMsg The display message string.
     */
    public DukeTaskFileParseException(String errorMsg, String displayMsg) {
        super(errorMsg, displayMsg);
    }
}
