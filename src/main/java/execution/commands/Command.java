package execution.commands;

import exception.DukeException;
import execution.Storage;
import execution.TaskList;
import execution.UI;

public abstract class Command {

    protected String descriptionOfTask;

    public Command(String descriptionOfTask) {
        this.descriptionOfTask = descriptionOfTask;
    }

    public void execute (TaskList tasklist, UI ui, Storage storage) throws DukeException {
        checkValidity();
    }

    protected void checkValidity() throws DukeException {
        return;
    }
}
