package commands;

import exception.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Interface for a command to be executed when the user inputs a command.
 */
public interface Command {

    boolean isExitCommand();

    String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
