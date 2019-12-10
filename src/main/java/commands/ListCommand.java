package commands;

import storage.Storage;
import ui.Ui;
import tasks.TaskList;

public class ListCommand extends Command {

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return (ui.showList() + "\n" + (tasks.printList()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
