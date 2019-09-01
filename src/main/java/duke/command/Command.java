package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that Duke will operate.
 */
public interface Command {
    /**
     * Returns should the command exit.
     *
     * @return whether should exit.
     */
    boolean isExit();

    /**
     * Executes the command.
     *
     * @param tasks  Tasks store in tasklist.
     * @param ui User interaction.
     * @param storage The storage area.
     * @throws DukeException  If there is mistake in operation.
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
