package command;

import exception.DeadlineException;
import exception.DukeParseException;
import exception.EventException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Abstract class for every Commands.
 * Encapsulates user's input into a command for easy handling.
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeParseException, DeadlineException, EventException;

    public abstract boolean isExit();

}
