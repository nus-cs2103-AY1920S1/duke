package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList();
        tasks.printList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
