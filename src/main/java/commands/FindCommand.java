package commands;

import logic.DukeException;
import logic.Storage;
import logic.TaskList;
import logic.Ui;
import task.Task;

import java.util.List;

/**
 * Encapsulates command to find and filter tasks by keyword.
 */
public class FindCommand extends Command {
    private String args;

    public FindCommand(String args) {
        this.args = args;
    }

    /**
     * Overridden Method to exit the program.
     *
     * @param tasks   list of tasks
     * @param ui      User Interface
     * @param storage File Storage and Management
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        List<Task> filteredTasks = tasks.findTask(args);
        ui.printList(filteredTasks, true);
    }
}
