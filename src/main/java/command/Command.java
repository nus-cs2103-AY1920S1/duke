package command;

import util.DukeException;
import util.Storage;
import util.TaskList;
import util.Ui;

import java.io.IOException;

public abstract class Command {

    protected boolean isExit;

    public Command() {
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage, String command) throws IOException, DukeException;

}
