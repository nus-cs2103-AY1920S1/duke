package duke.command;

import duke.exception.DukeExecutionException;
import duke.exception.DukeStorageException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import static duke.ui.Messages.TASKS_COUNT;
import static duke.ui.Messages.TASK_DELETED;

public class DeleteCommand extends SingleTaskCommand {
    public DeleteCommand(final Integer taskNumber) {
        super(taskNumber);
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws DukeExecutionException {
        check(tasks);
        CommandResult result = new CommandResult();
        Task task = tasks.deleteTask(this.taskNumber);
        result.addMessages(
            String.format("%s%n%s%n%s",
                TASK_DELETED,
                task.toString(),
                String.format(TASKS_COUNT, tasks.size())));
        try {
            storage.writeTasks(tasks);
        } catch (DukeStorageException e) {
            result.addWarnings(e.getMessage());
        }
        return result;
    }
}
