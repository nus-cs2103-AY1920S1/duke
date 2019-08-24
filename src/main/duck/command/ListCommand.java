package duck.command;

import duck.util.Storage;
import duck.util.TaskList;
import duck.util.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showFullList(taskList);
    }
}
