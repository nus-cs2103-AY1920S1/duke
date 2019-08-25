package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FinishCommand extends Command {
    private final int position;

    public FinishCommand(int position) {
        this.position = position;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task task = tasks.getTask(position);
        task.markAsDone();
        ui.showTaskDone(task);
    }
}
