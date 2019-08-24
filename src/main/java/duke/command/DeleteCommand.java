package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.deleteTask(taskId);
        storage.save(tasks);

        ui.print(" Noted. I've removed this task: ");

        Task task = tasks.getTask(taskId);
        ui.print("  " + task);

        ui.print(" Now you have "
                + tasks.size()
                + " tasks in the list.");
    }
}
