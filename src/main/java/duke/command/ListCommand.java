package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    @Override
    protected void check(TaskList tasks) {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        check(tasks);
        if (tasks.size() == 0) {
            ui.listNoTasks();
        } else {
            ui.listTasks(tasks);
        }
    }
}
