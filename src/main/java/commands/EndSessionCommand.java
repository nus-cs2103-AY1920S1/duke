package commands;

import components.Storage;
import components.Ui;
import components.TaskList;

public class EndSessionCommand implements Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.closeUi();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
