package duke.command;

import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printToUser(taskList.listAll());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
