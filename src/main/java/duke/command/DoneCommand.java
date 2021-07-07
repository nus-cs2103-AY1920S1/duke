package duke.command;

import duke.Duke;
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
     * @param duke Chat bot.
     * @param storage  Data file of chat bot.
     * @param taskList  Task list of chat bot.
     * @return Result of command.
     * @throws DukeException  If data file pointed to by storage cannot be updated.
     */
    @Override
    public String execute(Duke duke, Storage storage, TaskList taskList) throws DukeException {
        Task task = taskList.get(index);
        task.markAsDone();
        storage.update(taskList);
        assert(!isBye());
        return Ui.showDoneMessage(task);
    }
}
