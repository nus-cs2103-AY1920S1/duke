package command;

import duke.Storage;
import duke.Ui;
import task.TaskList;

public abstract class Command {
    protected boolean isExit = false;

    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return this.isExit;
    }
}
