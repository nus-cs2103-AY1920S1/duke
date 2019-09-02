package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Arrays;

public class DeleteCommand extends Command {
    private int[] taskIds;

    private static final String MESSAGE_DELETE   = "Noted. I've removed the following tasks:";
    private static final String MESSAGE_REMAINDER = "Now you have %d %s in the list.";

    private static final String ERROR_INVALID_TASK_ID = "The id of the task must be a number. e.g. done 1";

    /**
     * Constructs a Delete command.
     *
     * @param taskIds Ids of tasks to delete.
     */
    public DeleteCommand(int... taskIds) {
        Arrays.sort(taskIds);
        this.taskIds = taskIds;
    }

    /**
     * Executes Delete command to delete tasks from the given TaskList.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui.
     * @param storage Current Storage.
     * @throws DukeException If invalid id.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIds[0] < 1 || taskIds[taskIds.length - 1] > tasks.size()) {
            throw new DukeException(ERROR_INVALID_TASK_ID);
        }
        ui.append(MESSAGE_DELETE);
        for (int taskId : taskIds) {
            Task task = tasks.get(taskId - 1);
            ui.append(task.toString());
        }
        for (int i = taskIds.length - 1; i >= 0; i--) {
            tasks.remove(taskIds[i] - 1);
        }
        ui.append(String.format(MESSAGE_REMAINDER, tasks.size(), tasks.size() != 1 ? "tasks" : "task"));
    }
}
