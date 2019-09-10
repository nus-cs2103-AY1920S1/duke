package seedu.duke.model;

import seedu.duke.core.Storage;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.TaskListEmptyException;
import seedu.duke.model.dto.Task;

import java.io.IOException;
import java.util.List;

public class CmdDelete extends Cmd {

    public CmdDelete(String description, List<Task> taskList, Storage storage)
            throws IOException {
        String output = "";
        try {
            if (description.equals("") || description == null) {
                throw new DukeException("Entered index empty..");
            } else {
                int index = Integer.valueOf(description) - 1;
                output += storage.removeTask(output, taskList, index);
            }
        } catch (TaskListEmptyException e) {
            output += e;
        } catch (DukeException e) {
            output += e;
        }
        this.setMsg(output);
    }
}
