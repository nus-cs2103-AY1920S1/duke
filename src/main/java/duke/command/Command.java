package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }

    void persistState(TaskList tasks, Storage storage) throws DukeException {
        storage.saveDataToFile(tasks.getAllTasks());
    }
}
