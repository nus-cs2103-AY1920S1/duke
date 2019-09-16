package duke.command;

import java.util.Collections;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskComparator;

public class SortCommand extends Command {
    /**
     * Constructor for <code>SortCommand</code>.
     */
    public SortCommand() {
        super();
    }

    /**
     * Sorts the list according to task type and in chronological order.
     * @param tasks Instance of <code>TaskList</code> that stores <code>Task</code> objects.
     * @param ui Instance of <code>Ui</code> that handles user input and output.
     * @param storage Instance of <code>Storage</code> that handles writing and loading of information to hard disk.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Collections.sort(tasks.getList(), new TaskComparator());
        updateStorage(tasks, ui, storage);
        ListCommand listCommand = new ListCommand();
        listCommand.execute(tasks, ui, storage);
    }

    private void updateStorage(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.writeToFile(tasks);
        } catch (DukeException exception) {
            ui.printExceptionMessage(exception);
        }
    }

    /**
     * Checks if the current <code>Command</code> is an <code>ExitCommand</code>.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

}
