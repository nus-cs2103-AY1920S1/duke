package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Encapsulates a command to add task.
 */
public class AddCommand extends Command {
    protected final Task task;

    /**
     * Constructs an AddCommand object with a task to be added.
     *
     * @param task  Task to be added.
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Executes the command, adding the task to the task list.
     *
     * @param storage  Data file of chat bot.
     * @param taskList  Task list of chat bot.
     * @return Result of command.
     * @throws DukeException  If data file pointed to by storage cannot be updated.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DukeException {
        taskList.add(task);
        storage.update(taskList);
        assert(!isBye());
        return Ui.showAddMessage(taskList.size(), task);
    }
}
