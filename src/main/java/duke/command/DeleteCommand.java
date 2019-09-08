package duke.command;

import duke.exception.DukeException;
import duke.history.History;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends UndoableCommand {
    private String index;
    private Task removedTask;

    public DeleteCommand(String index) {
        this.index = index;
    }

    /**
     * Executes the delete command.
     * @param list List of tasks.
     * @param ui The user interface the user sees.
     * @param storage Stores the user's list of tasks.
     * @param history
     * @throws DukeException when an error occurs during execution.
     */
    @Override
    public void execute(Tasklist list, Ui ui, Storage storage, History history) throws DukeException {
        history.addExecutedCommand(this);

        try {
            String inputEntry = index.trim();
            int entry = Integer.parseInt(inputEntry) - 1;

            if (inputEntry.isEmpty() || entry < 0) {
                throw new IllegalArgumentException();
            }

            Task removedTask = list.remove(entry);
            this.removedTask = removedTask;

            super.commandOutput = ui.showDeleted(removedTask.toString(), list.size());

        } catch (IllegalArgumentException e) {
            throw new DukeException("You need to specify the task you want to delete!");
        }
    }

    /**
     * Undoes delete command.
     * @param tasklist List containing user's tasks.
     */
    @Override
    protected void undo(Tasklist tasklist) {
        tasklist.add(removedTask);
    }
}
