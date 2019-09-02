package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Arrays;

public class DoneCommand extends Command {
    private int[] taskIds;

    private static final String MESSAGE_DONE     = "Nice! I've marked the following tasks as done:";

    private static final String ERROR_INVALID_TASK_ID = "The id of the task must be a number. e.g. done 1";

    /**
     * Constructs a Done command.
     *
     * @param taskIds Ids of task to mark as done.
     */
    public DoneCommand(int... taskIds) {
        Arrays.sort(taskIds);
        this.taskIds = taskIds;
    }

    /**
     * Executes Done command to mark tasks from the given TaskList as done.
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
        ui.append(MESSAGE_DONE);
        for (int taskId : taskIds) {
            Task task = tasks.get(taskId - 1);
            task.markAsDone();
            ui.append(task.toString());
        }
    }
}
