package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UserInterface;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes listing of task list on user interface.
     *
     * @param taskList list of tasks.
     * @param ui user interface listing all tasks in task list.
     * @param storage local storage of data.
     */
    @Override
    public void execute(TaskList taskList, UserInterface ui, Storage storage) throws DukeException {
        ui.showTaskList(taskList.getTaskNames());
    }
}
