package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import exceptions.DukeException;

public abstract class Command {

    protected boolean isExit;
    protected String fullCommand;

    public boolean isExit() {
        return isExit;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

    }

}
