package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Encapsulates a command to delete task.
 */
public class DeleteCommand extends Command {
    protected final int index;

    /**
     * Constructs a DeleteCommand object with index of task to be deleted.
     *
     * @param index  Index of task to be deleted.
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the command, deleting the task associated with the index from the task list.
     *
     * @param storage  Data file of chat bot.
     * @param taskList  Task list of chat bot.
     * @return Result of command.
     * @throws DukeException  If data file pointed to by storage cannot be updated.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DukeException {
        Task task = taskList.remove(index);
        storage.update(taskList);
        assert(!isBye());
        return Ui.showDeleteMessage(taskList.size(), task);
    }
}
