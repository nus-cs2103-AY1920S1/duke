package duke.command;

import duke.DukeException;
import duke.common.Message;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UserInterface;

/**
 * Marks a task in the storage file as done using an index.
 */

public class DoneCommand extends Command {

    private int index;

    /**
     * Creates a command to mark a given task as done using an index.
     *
     * @param index index of the task to be marked as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes marking of a task as done on user interface.
     *
     * @param taskList list of tasks.
     * @param ui user interface displaying the successful marking of a task as done.
     * @param storage local storage of data.
     */
    @Override
    public void execute(TaskList taskList, UserInterface ui, Storage storage) throws DukeException {
        if (index <= 0 || index > taskList.size()) {
            throw new DukeException(Message.MESSAGE_INVALID_TASK_INDEX);
        }
        Task task = taskList.markDone(index);
        storage.save(taskList.getSimplifiedTaskRepresentations());
        ui.showMarkDone(task);
    }
}
