package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {

    protected TaskList tasks;
    protected Ui ui;
    protected Storage storage;
    protected boolean isExit;

    public abstract void execute() throws DukeException;

    public void setData(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    public boolean isExit() {
        return isExit;
    }
}
