package cs2103t.duke.command;

import cs2103t.duke.file.Storage;
import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks(ui);
    }
}
