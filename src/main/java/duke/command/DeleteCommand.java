package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Message.MESSAGE_DELETE;
import static duke.ui.Message.concatLines;
import static duke.ui.Message.getTaskTotalMsg;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.remove(taskId);
        storage.save(tasks);
        return concatLines(MESSAGE_DELETE, task.getIndentedFormat(), getTaskTotalMsg(tasks));
    }
}