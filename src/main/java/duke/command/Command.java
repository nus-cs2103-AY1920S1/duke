package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class Command {
    /**
     * Executes the command given the TaskList, Ui and Storage.
     *
     * @param tasks   The given TaskList.
     * @param ui      The given Ui.
     * @param storage The given Storage.
     * @throws DukeException DukeException when there is an error executing the task.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    }

    /**
     * Is exit boolean.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}

