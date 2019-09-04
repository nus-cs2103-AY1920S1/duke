package duke.commands;

import duke.DukeException;
import duke.tasks.TaskList;
import duke.Storage;
import duke.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
