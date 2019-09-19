package duke.command;

import duke.DukeException;
import duke.common.MessageUtils;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

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
     * Executes deletion of a task.
     *
     * @param dukeResponse response from Duke to user.
     * @param taskList list of tasks.
     * @param storage local storage of data.
     */
    @Override
    public void execute(DukeResponse dukeResponse, TaskList taskList, Storage storage) throws DukeException {
        if (index <= 0 || index > taskList.size()) {
            throw new DukeException(MessageUtils.MESSAGE_INVALID_TASK_INDEX);
        }
        Task task = taskList.delete(index);
        assert task != null;
        storage.save(taskList.getSimplifiedTaskRepresentations());
        dukeResponse.add(MessageUtils.MESSAGE_DELETED);
        dukeResponse.add(" " + task);
        dukeResponse.add(String.format(MessageUtils.MESSAGE_SHOW_TASK_SIZE, taskList.size()));
    }
}
