package seedu.duke.commands.base;

import seedu.duke.exceptions.DukeException;
import seedu.duke.util.Storage;
import seedu.duke.util.TaskList;
import seedu.duke.util.UI;

import java.io.IOException;

/**
 * Command to set task in TaskList to done.
 */
public class DoneCommand extends Command {

    private int entry;

    /**
     * Constructor to provide the entry number to set done.
     *
     * @param entry Entry number of the TaskList to set done.
     */
    public DoneCommand(int entry) {
        this.entry = entry;
    }

    /**
     * Marks the task as done, informs the user that the task has been set as done and then writes
     * the new change to the text file.
     *
     * @param tasks TaskList of tasks to delete from.
     * @param ui UI to inform the user of deletion.
     * @param storage Storage to write after deletion.
     * @throws DukeException Throws if storage cannot find the file to write to.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        tasks.markAsDone(entry);
        try {
            storage.writeToFile(tasks);
        } catch (IOException ex) {
            throw new DukeException("OOPS!!! I cannot read your file! :(");
        }

        return ui.showDone(tasks.getTask(entry));
    }

}
