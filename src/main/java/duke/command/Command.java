package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
