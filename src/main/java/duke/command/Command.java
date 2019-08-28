package duke.command;

import duke.task.Task;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public abstract class Command {
    protected String args;
    protected Task task;

    public Command() {
    }

    public Command(String args) {
        this.args = args;
    }

    public Command(Task task) {
        this.task = task;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
