package seedu.duke.model;

import seedu.duke.core.Storage;
import seedu.duke.exception.DukeException;
import seedu.duke.model.dto.Task;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class CmdEvent extends Cmd {
    public CmdEvent(String description, String cmd, List<Task> list, Storage storage)
            throws ParseException, IOException, DukeException {
        this.setMsg(super.handleEventOrDeadline(description, cmd,
                " /at ", list, storage));
    }
}
