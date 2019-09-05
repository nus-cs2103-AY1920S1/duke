package command;

import task.TaskList;
import util.Storage;

public abstract class Command {
    protected boolean isExit;
    protected String command;

    public Command() {
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void executeCommand(TaskList taskList, Storage storage);
}
