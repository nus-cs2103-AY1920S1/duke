package seedu.duke.model;

import seedu.duke.core.Storage;
import seedu.duke.core.Ui;
import seedu.duke.exception.DukeException;
import seedu.duke.model.dto.Task;

import java.io.IOException;
import java.util.List;

public class CmdDone extends Cmd{

    public CmdDone(String description, List<Task> taskList,
                   Ui ui, Storage storage) throws DukeException, IOException{
        super(description);

        if (description.equals("") || description == null) {
            throw new DukeException("Oops! list index not entered for 'done'");
        } else {

            int index = Integer.valueOf(description) - 1;
            taskList.get(index).markAsDone();

            storage.saveTask(taskList);
            String message = "Nice! I've marked this task as done:\n"
                    + ui.displayTask(this.getMsg(), taskList, index);
            this.setMsg(message);
        }
    }
}
