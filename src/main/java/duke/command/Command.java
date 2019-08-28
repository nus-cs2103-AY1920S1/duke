package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public abstract class Command {
    /**
     * Executes command.
     *
     * @param tasks Current TaskList.
     * @param storage Current Storage.
     * @throws DukeException If invalid input.
     */
    public abstract void execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Returns true if it is an ExitCommand.
     *
     * @return true if ExitCommand.
     */
    public boolean isExit() {
        return false;
    }
}
