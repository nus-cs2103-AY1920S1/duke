package cs2103t.duke.command;

import cs2103t.duke.file.Storage;
import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;

public abstract class Command {
    protected boolean isExit;

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        return this.isExit;
    }
}
