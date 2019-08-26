package command;

import exception.DeadlineException;
import exception.DukeParseException;
import exception.EventException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeParseException, DeadlineException, EventException;
    public abstract boolean isExit();

}
