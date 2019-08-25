package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private final int position;

    public DeleteCommand(int position) {
        this.position = position;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task oldTask = tasks.getTask(position);
        tasks.deleteTask(position);
        ui.showTaskDeleted(oldTask);
        ui.showNumTasks(tasks.getNumTasks());
    }
}
