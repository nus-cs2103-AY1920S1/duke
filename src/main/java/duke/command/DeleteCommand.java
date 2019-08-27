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
     * @param ui  User interface of chat bot.
     * @throws DukeException  If data file pointed to by storage cannot be updated.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        Task task = taskList.remove(index);
        storage.update(taskList);
        ui.showDeleteMessage(taskList.size(), task);
    }
}
