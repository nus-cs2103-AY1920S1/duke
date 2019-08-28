package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Bye extends Command{


    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
