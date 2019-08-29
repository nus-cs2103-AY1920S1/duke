package Commands;

import Storage.Storage;
import Tasks.TaskList;
import Ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public abstract boolean isExit();
}
