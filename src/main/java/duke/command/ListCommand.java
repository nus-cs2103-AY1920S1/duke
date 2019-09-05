package duke.command;

import duke.DukeException;
import duke.common.Message;
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
    public String execute(TaskList taskList, UserInterface ui, Storage storage) throws DukeException {
        StringBuilder output = new StringBuilder(String.format(Message.MESSAGE_SHOW_TASK_LIST, ""));
        for (String taskName : taskList.getTaskNames()) {
            output.append("\n" + taskName);
        }
        return output.toString();
//        ui.showTaskList(taskList.getTaskNames());
    }
}
