package command;

import exception.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;


public abstract class Command {

    public abstract boolean isExit();

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
