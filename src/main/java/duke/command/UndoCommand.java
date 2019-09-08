package duke.command;

import duke.exception.DukeException;
import duke.history.History;
import duke.storage.Storage;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

/**
 * Represents an undo command. It is not an UndoableCommand itself, because in many cases
 * a user's repeated undo commands aims to undo previous actions, not to undo the undo action.
 */
public class UndoCommand extends Command {

    /**
     * Executes an undo command.
     * @param list List of tasks.
     * @param ui The user interface the user sees.
     * @param storage Stores the user's list of tasks.
     * @param history
     * @throws DukeException when an error occurs during execution.
     */
    @Override
    public void execute(Tasklist list, Ui ui, Storage storage, History history) throws DukeException {
        UndoableCommand c = history.getExecutedCommand();
        c.undo(list);
        super.commandOutput = c.undoneMessage() + c.toString();
    }
}
