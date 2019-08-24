package duke.command;

import duke.Duke;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private int taskId;

    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(taskId);

        if (task.getIsDone()) {
            throw new DukeException("This task is already marked as done.");
        }

        task.markAsDone();
        storage.save(tasks);

        ui.print(" Nice! I've marked this task as done: ");
        ui.print("  " + task);
    }
}
