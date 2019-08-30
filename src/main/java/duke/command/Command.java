package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.task.TaskList;
import duke.util.Ui;

public abstract class Command {
    protected Storage storage;
    protected TaskList taskList;
    protected Ui ui;
    protected boolean isExit;

    public Command() {
        this.isExit = false; // commands are non-exiting by default
    }

    public void initialize(Storage storage, TaskList taskList, Ui ui) {
        this.storage = storage;
        this.taskList = taskList;
        this.ui = ui;
    }

    public abstract void execute() throws DukeException;

    public boolean isExit() {
        return this.isExit;
    }
}
