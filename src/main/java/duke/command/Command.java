package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

public abstract class Command {
    public abstract String execute(TaskList taskList, Storage storage);

    public abstract boolean isExit();
}
