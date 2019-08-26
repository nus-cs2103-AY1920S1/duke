package duke.storage;

import duke.DukeExceptions;

public class DukeFileWriteException extends DukeExceptions {
    DukeFileWriteException(String displayMsg) {
        super("Error encountered while writing task data to disk", displayMsg);
    }

    DukeFileWriteException(String errorMsg, String displayMsg) {
        super(errorMsg, displayMsg);
    }
}
