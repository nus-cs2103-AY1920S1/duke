package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;
import duke.util.UiMessage;
import duke.util.exception.DukeException;
import duke.util.exception.ExceptionType;

public class ListCommand implements Command {

    /**
     * Lists all tasks currently in the list.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @param storage Object that handles storage of task list to disk.
     * @throws DukeException Application-specific exception thrown during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException(ExceptionType.TASK_LIST_EMPTY);
        }
        ui.showMessage(UiMessage.TASK_LIST);
        ui.showTasks(tasks);
    }
}
