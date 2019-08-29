package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public class Command {
    boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void execute(Ui ui, Storage storage, TaskList allTasks) throws DukeException {
        //Default behaviour is to save current TaskList
        storage.save(allTasks);
    }
}
