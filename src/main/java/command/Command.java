package command;

import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;

public abstract class Command {

    private boolean isExit;
    public Command() {
        this.isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return isExit;
    }

    public void canExit() { this.isExit = true; }
}
