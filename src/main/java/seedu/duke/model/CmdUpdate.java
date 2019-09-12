package seedu.duke.model;

import seedu.duke.exception.DukeException;
import seedu.duke.model.dto.Deadline;
import seedu.duke.model.dto.Event;
import seedu.duke.model.dto.Task;

public class CmdUpdate extends Cmd {
    public CmdUpdate(Task task, String description, String byOrAt) throws DukeException {
        if (task instanceof Event || task instanceof Deadline) {
            
        } else {
            throw new DukeException("Task must be either Deadline or Event type!");
        }
    }

    //constructor for updating subclass Todo
    public CmdUpdate(Task task, String description) {

    }
}
