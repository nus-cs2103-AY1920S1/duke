package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Messages.TASKS_COUNT;
import static duke.ui.Messages.TASK_ADD_FAILURE;
import static duke.ui.Messages.TASK_ADD_SUCCESS;
import static duke.ui.Messages.TASK_MISSING_DESCRIPTION;

public abstract class AddCommand extends Command {
    protected String description;

    public AddCommand(final String description) {
        this.description = description;
    }

    @Override
    protected void check(final TaskList tasks) throws DukeException {
        if (this.description.isBlank()) {
            throw new DukeException(TASK_MISSING_DESCRIPTION);
        }
    }

    protected void addTask(final Task task, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.addTask(task)) {
            storage.writeTasks(tasks);
            ui.showMessage(TASK_ADD_SUCCESS);
            ui.showIndented(task.toString());
            ui.showMessage(String.format(TASKS_COUNT, tasks.size()));
        } else {
            throw new RuntimeException(TASK_ADD_FAILURE);
        }
    }
}
