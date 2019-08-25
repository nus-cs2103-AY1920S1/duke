package duke.exception;

import duke.ui.CmdUx;

public class DukeDeadlineException extends DukeException {

    public DukeDeadlineException() {
        super();
    }

    public DukeDeadlineException(String message) {
        super(message);
        CmdUx.printHBars(message);
    }

}
