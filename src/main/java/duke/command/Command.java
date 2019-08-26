package duke.command;

import duke.TaskList;
import duke.error.DukeException;
import duke.util.Ui;
import duke.util.Storage;

public interface Command {
    /**
     * Executes the command.
     * @param task TaskList
     * @param ui Ui
     * @param storage Storage
     */
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException;

    /**
     * Check if it should exit.
     * @return boolean
     */
    public boolean isExit();
}
