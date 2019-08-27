package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Encapsulates a command to mark a task as done.
 */
public class DoneCommand extends Command {
    protected final int index;

    /**
     * Constructs a DoneCommand object with index of task to be marked as done.
     *
     * @param index  Index of task to be marked as done.
     */
    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the command, marking the task associated with the index as done.
     *
     * @param storage  Data file of chat bot.
     * @param taskList  Task list of chat bot.
     * @param ui  User interface of chat bot.
     * @throws DukeException  If data file pointed to by storage cannot be updated.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        Task task = taskList.get(index);
        task.markAsDone();
        storage.update(taskList);
        ui.showDoneMessage(task);
    }
}
