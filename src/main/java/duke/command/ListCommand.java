package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Messages.LIST_NO_TASKS;
import static duke.ui.Messages.LIST_TASKS;

public class ListCommand extends Command {
    @Override
    protected void check(TaskList tasks) {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        check(tasks);
        if (tasks.size() == 0) {
            ui.showMessage(LIST_NO_TASKS);
        } else {
            ui.showMessage(LIST_TASKS);
            ui.showIndented(tasks.toString().split("\\n"));
        }
    }
}
