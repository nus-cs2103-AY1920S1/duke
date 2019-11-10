package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Message;

/**
 * DeleteCommand class used to delete tasks from storage.
 */
public class DeleteCommand extends Command {

    /** Index of item to delete. **/
    private int deleteIndex;

    /**
     * Initialises a DeleteCommand.
     * @param deleteIndex Index of the item to be deleted.
     */
    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public CommandResult execute(TaskList taskList) throws DukeException {
        Task deletedTask = taskList.get(deleteIndex);
        taskList.delete(deleteIndex);
        return new CommandResult(Message.showDeleteSuccess(deletedTask, taskList));
    }
}
