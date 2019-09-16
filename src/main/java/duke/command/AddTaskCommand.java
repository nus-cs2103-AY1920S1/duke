package duke.command;

import duke.exception.DukeInvalidCommandException;
import duke.exception.DukeStorageException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Messages.TASK_ADD_FAILURE;
import static duke.ui.Messages.TASK_MISSING_DESCRIPTION;

/**
 * Command that adds a new Task.
 */
public abstract class AddTaskCommand extends Command {
    protected final String description;

    public AddTaskCommand(final String description) {
        this.description = description;
    }

    @Override
    protected void check(final TaskList tasks) throws DukeInvalidCommandException {
        if (this.description.isBlank()) {
            throw new DukeInvalidCommandException(TASK_MISSING_DESCRIPTION);
        }
    }

    /**
     * Adds a Task to the current TaskList.
     * @param task the Task to be added
     * @param tasks the TaskList to add the Task to
     * @param ui the user interface
     * @param storage the storage interface
     */
    protected void addTask(final Task task, TaskList tasks, Ui ui, Storage storage) {
        if (tasks.addTask(task)) {
            ui.addTaskSuccess(task, tasks);
            try {
                storage.writeTasks(tasks);
            } catch (DukeStorageException e) {
                ui.showWarning(e.getMessage());
            }
        } else {
            throw new RuntimeException(TASK_ADD_FAILURE);
        }
    }
}
