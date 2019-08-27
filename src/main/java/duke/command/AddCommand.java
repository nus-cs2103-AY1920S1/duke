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
     * @param ui  User interface of chat bot.
     * @throws DukeException  If data file pointed to by storage cannot be updated.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        taskList.add(task);
        storage.update(taskList);
        ui.showAddMessage(taskList.size(), task);
    }
}
