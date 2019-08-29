package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Mark a task as done.
 */
public class CompleteCommand extends Command {
    private int taskId;

    public CompleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.get(this.taskId);
        task.markAsDone();
        storage.save(tasks);
        ui.showTaskCompletionMsg(task);
    }
}