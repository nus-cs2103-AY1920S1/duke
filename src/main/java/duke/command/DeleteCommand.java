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

    /**
     * Constructs a DeleteCommand object given taskId of the task to be deleted.
     *
     * @param taskId the 1-based index of the task to be deleted
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTaskByIndex(taskId);
        storage.save(tasks);
        return concatLines(MESSAGE_DELETE, task.getIndentedFormat(), getTaskTotalMsg(tasks));
    }
}