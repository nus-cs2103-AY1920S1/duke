package duke.command;

import duke.exception.DukeInvalidCommandException;
import duke.exception.DukeStorageException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import static duke.ui.Messages.TASKS_COUNT;
import static duke.ui.Messages.TASK_ADD_FAILURE;
import static duke.ui.Messages.TASK_ADD_SUCCESS;
import static duke.ui.Messages.TASK_MISSING_DESCRIPTION;

public abstract class AddCommand extends Command {
    protected final String description;

    public AddCommand(final String description) {
        this.description = description;
    }

    @Override
    protected void check(final TaskList tasks) throws DukeInvalidCommandException {
        if (this.description.isBlank()) {
            throw new DukeInvalidCommandException(TASK_MISSING_DESCRIPTION);
        }
    }

    protected CommandResult addTask(final Task task, TaskList tasks, Storage storage) {
        CommandResult result = new CommandResult();
        if (tasks.addTask(task)) {
            result.addMessages(
                TASK_ADD_SUCCESS,
                task.toString(),
                String.format(TASKS_COUNT, tasks.size())
            );
            try {
                storage.writeTasks(tasks);
            } catch (DukeStorageException e) {
                result.addWarnings(e.getMessage());
            }
        } else {
            throw new RuntimeException(TASK_ADD_FAILURE);
        }
        return result;
    }
}
