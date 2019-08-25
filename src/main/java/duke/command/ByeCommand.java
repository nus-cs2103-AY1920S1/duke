package duke.command;

import duke.logic.Storage;
import duke.logic.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}
