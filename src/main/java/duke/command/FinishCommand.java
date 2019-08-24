package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FinishCommand extends Command {
    private final String posString;

    public FinishCommand(String posString) {
        this.posString = posString;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        try {
            int position = Integer.parseInt(this.posString) - 1;
            Task task = tasks.getTask(position);
            task.markAsDone();
            ui.showTaskDone(task);
        } catch (NumberFormatException e) {
            throw new DukeException("Your input should be a number.");
        }
    }
}
