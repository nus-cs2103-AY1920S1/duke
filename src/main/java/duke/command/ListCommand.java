package duke.command;

import duke.data.Storage;
import duke.object.TaskList;
import duke.user.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
