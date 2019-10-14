package duke.command;

import duke.exception.DukeException;

import duke.ui.Ui;

import duke.task.TaskList;

import duke.storage.Storage;

public abstract class Command {

    /**
     * Execute the corresponding commands.
     *
     * @param taskList TaskList object containing the current tasks list.
     * @param ui The Ui object we are currently using.
     * @param storage The Storage object we are currently using.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the command is an ExitCommand.
     *
     * @return False by default. To be overridden in ExitCommand class.
     */
    public boolean isExit() {
        return false;
    }
}
