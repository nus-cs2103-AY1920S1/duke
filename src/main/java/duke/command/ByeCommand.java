package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
        return true;
    }

}
