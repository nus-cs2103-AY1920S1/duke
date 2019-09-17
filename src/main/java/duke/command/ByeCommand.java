package duke.command;

import duke.exception.DukeException;
import duke.history.History;
import duke.storage.Storage;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

/**
 * Represents a command to say goodbye.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        super.isExit = true;
    }

    /**
     * Tells the user goodbye.
     * @param list List of tasks.
     * @param ui The user interface the user sees.
     * @param storage Stores the user's list of tasks.
     * @param history
     * @throws DukeException when an error occurs during execution.
     */
    @Override
    public void execute(Tasklist list, Ui ui, Storage storage, History history) throws DukeException {
        storage.store(list.tasks);
        super.commandOutput = ui.showFarewell();
    }
}
