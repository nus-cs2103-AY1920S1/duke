package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ShowCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
