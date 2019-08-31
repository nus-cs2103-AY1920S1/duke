package duke.command;

import duke.DukeException;
import duke.common.Message;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UserInterface;

/**
 * Deletes a task from the storage file.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a command to delete a given task using an index.
     *
     * @param index index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes deletion of a task on user interface.
     *
     * @param taskList list of tasks.
     * @param ui user interface displaying the successful deletion of a task.
     * @param storage local storage of data.
     */
    @Override
    public void execute(TaskList taskList, UserInterface ui, Storage storage) throws DukeException {
        if (index <= 0 || index > taskList.size()) {
            throw new DukeException(Message.MESSAGE_INVALID_TASK_INDEX);
        }
        Task task = taskList.delete(index);
        storage.save(taskList.getSimplifiedTaskRepresentations());
        ui.showDeletion(task);
        ui.showTaskSize(taskList);
    }
}
