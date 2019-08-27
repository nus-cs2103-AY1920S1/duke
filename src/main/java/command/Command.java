package command;

import exception.*;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Abstract class for every Commands.
 * Encapsulates user's input into a command for easy handling.
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();

}
