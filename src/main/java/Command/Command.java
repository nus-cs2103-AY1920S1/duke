package Command;

import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

import java.io.IOException;

public abstract class Command {

    protected boolean isExit = false;

    public Command() {
    }

    public abstract void execute(TaskList t, Ui ui, Storage storage) throws IOException;

    public abstract boolean isExit();
}
