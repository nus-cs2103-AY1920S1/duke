package duke.storage;

import duke.DukeExceptions;

class DukeTaskFileParseException extends DukeExceptions {
    public DukeTaskFileParseException(String displayMsg) {
        super("Error encountered while parsing task data file", displayMsg);
    }

    public DukeTaskFileParseException(String errorMsg, String displayMsg) {
        super(errorMsg, displayMsg);
    }
}
