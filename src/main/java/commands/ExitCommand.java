package commands;

import storage.Storage;
import ui.Ui;
import tasks.TaskList;

public class ExitCommand extends Command {

    private boolean isExit = true;

    @Override
    public boolean isExit() {
        return this.isExit;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return (ui.closingStatement());
    }
}
