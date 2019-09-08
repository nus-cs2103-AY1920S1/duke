package duke.command;

import duke.exception.DukeException;
import duke.history.History;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

/**
 * Represents a done command, which masks a task as done.
 */
public class DoneCommand extends Command {
    private String index;

    public DoneCommand(String index) {
        this.index = index;
    }

    /**
     * Marks the task at the given index as done.
     * @param list List of tasks.
     * @param ui The user interface the user sees.
     * @param storage Stores the user's list of tasks.
     * @param history
     * @throws DukeException when an error occurs during execution.
     */
    @Override
    public void execute(Tasklist list, Ui ui, Storage storage, History history) throws DukeException {
        try {
            String inputEntry = index.trim();
            int entry = Integer.parseInt(inputEntry) - 1;

            if (inputEntry.isEmpty() || entry < 0) {
                throw new IllegalArgumentException();
            }

            Task task = list.get(entry);
            task.markAsDone();

            super.commandOutput = ui.showDone(task.toString());

        } catch (IllegalArgumentException e) {
            throw new DukeException("You need to specify the task you want to complete!");
        } // End of try-catch.

    } // End of method.
}
