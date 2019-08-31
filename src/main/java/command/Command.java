package command;

import task.TaskList;
import duke.UserInterface;
import duke.Storage;

public abstract class Command {
    boolean isExit = false;

    public abstract void execute(TaskList tasks, UserInterface ui, Storage storage);

    public boolean isExit() {
        return isExit;
    }
}
