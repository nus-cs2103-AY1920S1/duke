package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class EditCommand extends Command {
    private final int position;
    private final String newDescription;

    public EditCommand(int position, String newDescription) {
        this.position = position;
        this.newDescription = newDescription;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task task = tasks.getTask(this.position);
        task.updateDescription(this.newDescription);
        ui.showTaskEdited(task);
    }
}
