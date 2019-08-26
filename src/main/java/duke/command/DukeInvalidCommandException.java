package duke.command;

import duke.DukeExceptions;

class DukeInvalidCommandException extends DukeExceptions {
    DukeInvalidCommandException(String displayMsg) {
        super("Invalid user command inputted", displayMsg);
    }

    DukeInvalidCommandException(String errorMsg, String displayMsg) {
        super(errorMsg, displayMsg);
    }
}
