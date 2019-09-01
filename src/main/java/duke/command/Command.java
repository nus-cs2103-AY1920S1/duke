package duke.command;

import duke.task.TaskList;
import duke.ui.UI;
import duke.storage.Storage;

/**
 * Represents a command that the user typed
 */

public abstract class Command {
    String command;

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws Exception;
    public abstract boolean isExit();
    public abstract boolean equals(Object o);
}
