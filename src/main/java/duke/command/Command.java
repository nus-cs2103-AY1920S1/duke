package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class Command {
    protected boolean isExit = false;
    protected Task task;

    /**
     * Execute the command.
     *
     * @param tasks List of tasks in memory
     * @param ui Ui instance
     * @param storage Storage instance
     * @throws DukeException If command word is invalid
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    }

    /**
     * Getter for variable isExit.
     * @return Whether program should exit.
     */
    public boolean isExit() {
        return isExit;
    }
}
