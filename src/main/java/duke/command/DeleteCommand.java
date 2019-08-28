package duke.command;

import duke.task.Task;
import duke.core.TaskList;
import duke.core.Ui;
import duke.core.Storage;
import duke.core.DukeException;

public class DeleteCommand extends Command {
    private int taskId;

    public DeleteCommand(int taskId) {
        super();
        this.taskId = taskId;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.getTask(taskId - 1);
        tasks.removeTask(taskId - 1);
        ui.removedTask(t, tasks.getSize());
        storage.save(tasks);
    }
}


