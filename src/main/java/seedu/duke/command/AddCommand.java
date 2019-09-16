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
     * Executes the add command, which saves the task into the given task list.
     * Displays status message on ui, and stores (if applicable) in storage
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.add(task);
            ui.showSuccessMessage("adding", task);
            storage.store(tasks);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
