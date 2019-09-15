package seedu.duke.commands.base;

import seedu.duke.exceptions.DukeException;
import seedu.duke.tasks.Task;
import seedu.duke.util.Storage;
import seedu.duke.util.TaskList;
import seedu.duke.util.UI;

import java.io.IOException;

/**
 * Command to delete from the TaskList class.
 */
public class DeleteCommand extends Command {

    private int entry;

    /**
     * Constructor to provide the entry number to delete.
     *
     * @param entry Entry number of the TaskList to delete.
     */
    public DeleteCommand(int entry) {
        this.entry = entry;
    }

    /**
     * Informs the user that the task is going to be deleted, actually delete the task from TaskList,
     * then writes the new change to the text file.
     *
     * @param tasks TaskList of tasks to delete from.
     * @param ui UI to inform the user of deletion.
     * @param storage Storage to write after deletion.
     * @throws DukeException Throws if storage cannot find the file to write to.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        Task toDelete = tasks.getTask(entry);
        tasks.deleteFromList(entry);
        try {
            storage.writeToFile(tasks);
        } catch (IOException ex) {
            throw new DukeException("OOPS!!! I cannot read your file! :(");
        }

        return ui.operateTask(toDelete, tasks, false);
    }
}
