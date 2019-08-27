package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UserInterface;

public abstract class Command {

    public boolean isTerminated() {
        return false;
    }

    public abstract void execute(TaskList taskList, UserInterface ui, Storage storage) throws DukeException;

}
