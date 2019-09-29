package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Deletes a task and saves the program state.
 */
public class DeleteCommand extends Command {
    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(taskId);
        storage.save(tasks);

        ui.printLine("Noted. I've removed this task:");
        ui.printLine(task);
        ui.printLine("Now you have "
                + tasks.size()
                + " tasks in the list.");
    }
}
