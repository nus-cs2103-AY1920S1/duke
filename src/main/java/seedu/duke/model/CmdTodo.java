package seedu.duke.model;

import seedu.duke.core.Storage;
import seedu.duke.exception.DukeException;
import seedu.duke.model.dto.Task;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class CmdTodo extends Cmd {
    public CmdTodo(Storage storage, String output, List<Task> list, String cmd,
                   String description, String input2) throws ParseException,
            IOException, DukeException {
        this.setMsg(storage.addTask(output, list, cmd,
                description, "__dummy__"));
    }
}
