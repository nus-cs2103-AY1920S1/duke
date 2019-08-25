package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.task.Task;

public abstract class Command {
    int n;
    protected Task task;
    boolean exit = false;

    public abstract void execute(TaskList t, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.exit;
    }
}