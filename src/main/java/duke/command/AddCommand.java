package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class AddCommand extends Command {

    private Task addedTask;

    public AddCommand(Task t) {
        this.addedTask = t;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(addedTask);
        ui.printAddedTask(addedTask, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
