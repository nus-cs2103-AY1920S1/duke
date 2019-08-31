package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showLine();
        ui.list(taskList);
        ui.showLine();
    }
}
