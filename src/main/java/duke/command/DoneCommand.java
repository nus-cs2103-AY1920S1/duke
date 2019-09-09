package duke.command;

import duke.exception.DukeExecutionException;
import duke.exception.DukeStorageException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import static duke.ui.Messages.TASK_ALREADY_DONE;
import static duke.ui.Messages.TASK_MARKED_AS_DONE;

public class DoneCommand extends SingleTaskCommand {
    public DoneCommand(final Integer taskNumber) {
        super(taskNumber);
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws DukeExecutionException {
        check(tasks);
        CommandResult result = new CommandResult();
        Task task = tasks.getTask(this.taskNumber);
        if (task.isDone()) {
            result.addWarnings(TASK_ALREADY_DONE);
        } else {
            task.markAsDone();
            result.addMessages(String.format("%s%n%s", TASK_MARKED_AS_DONE, task.toString()));
            try {
                storage.writeTasks(tasks);
            } catch (DukeStorageException e) {
                result.addWarnings(e.getMessage());
            }
        }
        return result;
    }
}
