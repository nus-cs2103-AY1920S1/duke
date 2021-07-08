package duke;

import duke.ui.ErrorMsg;
import duke.ui.ErrorMsgWithParams;

public class DukeException extends Exception {
    public DukeException(ErrorMsg msgCode) {
        super(msgCode.getDescription());
    }

    public DukeException(ErrorMsgWithParams msgCode, String... parameters) {
        super(msgCode.getDescription(parameters));
        assert parameters.length > 0;
    }
}
