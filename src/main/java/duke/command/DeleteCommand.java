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
    private int index;

    /**
     * Constructs a DeleteCommand object given index of the task to be deleted.
     *
     * @param index the 1-based index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTaskByIndex(this.index);
        storage.save(tasks);
        tasks.commit();
        return concatLines(MESSAGE_DELETE, task.getIndentedFormat(), getTaskTotalMsg(tasks));
    }
}