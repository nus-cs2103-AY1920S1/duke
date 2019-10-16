package duke.command;

import duke.task.TaskList;
import duke.ui.UI;
import duke.storage.Storage;

/**
 * Represents a command that is able to execute and perform the command given by user.
 */

public abstract class Command {
    String command;

    public abstract String execute(TaskList tasks, UI ui, Storage storage) throws Exception;

    public abstract boolean isExit();

    public abstract boolean equals(Object o);

    public abstract String getCommand();
}
