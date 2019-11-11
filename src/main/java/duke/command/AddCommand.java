package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
    Task task;

    public AddCommand(Task task) {
        assert !task.equals(null) : "Task cannot be null";
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        storage.save(tasks);
        ui.showAddTaskMsg(task);
    }
}
