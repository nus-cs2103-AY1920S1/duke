package duke.command;

import duke.task.Task;
import duke.core.TaskList;
import duke.core.Ui;
import duke.core.Storage;
import duke.core.DukeException;

public class DoneCommand extends Command {
    private int taskId;

    public DoneCommand(int taskId) {
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
        t.markAsDone();
        storage.save(tasks);
        ui.markedAsDone(t);
    }
}
