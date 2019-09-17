package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class RemindCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return (tasks.findReminders());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
