package duke.command;

import duke.storage.Storage;
import duke.tasklist.MyList;
import duke.ui.UserInterface;

public class ListCommand extends Command {

    @Override
    public void execute(MyList taskList, UserInterface ui, Storage storage) {
        ui.printList(taskList);
    }
}
