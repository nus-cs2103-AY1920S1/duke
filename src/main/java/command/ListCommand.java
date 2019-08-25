package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.getList());
    }

    public boolean isExit() {
        return false;
    }
}
