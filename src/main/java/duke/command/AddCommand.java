package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.task.Task;

public class AddCommand extends Command {
    public AddCommand(Task task) {
        super.task = task;
    }

    public void execute(TaskList t, Ui ui, Storage storage) {
        t.tasks.add(task);
        ui.showAddedTask(task, t.tasks.size());
    }
}
