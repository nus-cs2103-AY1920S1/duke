package command;

import exception.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;


abstract public class Command {

    abstract public boolean isExit();

    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
