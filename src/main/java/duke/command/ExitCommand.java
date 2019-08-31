package duke.command;

import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
