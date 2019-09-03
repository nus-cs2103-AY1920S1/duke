package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ByeCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.byeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}