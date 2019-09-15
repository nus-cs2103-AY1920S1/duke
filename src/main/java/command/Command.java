package command;

import task.TaskList;
import util.Storage;

import java.io.IOException;

public abstract class Command {
    boolean isExit;
    protected String command;

    Command() {
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void executeCommand(TaskList taskList, Storage storage) throws IOException;
}
