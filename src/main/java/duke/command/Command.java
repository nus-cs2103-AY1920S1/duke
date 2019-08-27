package duke.command;

import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
