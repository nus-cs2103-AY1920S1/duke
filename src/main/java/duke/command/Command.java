package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public interface Command {
    /**
     * Executes the Duke command.
     * 
     * @param tasks   the TaskList to perform modifications on.
     * @param ui      the Ui Object to perform output on.
     * @param storage the Storage Object to save tasks to.
     * @throws DukeException user friendly error, usually caused by poor input.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns a boolean indicating if this Command is an exit Command.
     * 
     * @return a boolean indicating if this Command is an exit Command.
     */
    public default boolean isExit() {
        return false;
    }
}