package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

public class AddCommand extends Command {
    private Task task;

    /**
     * Creates a new AddCommand with a task.
     * 
     * @param task The task to be added in the command.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command, which saves the task into the given task list. Displays status
     * message on ui, and stores (if applicable) in storage
     *
     * @param tasks   the task list to add to
     * @param ui      the user interface to show information
     * @param storage the storage to update task list in
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.add(task);
            storage.store(tasks);
            return ui.getSuccessMessage("adding", task);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
