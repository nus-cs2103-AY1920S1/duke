package duke.command;

import duke.storage.DukeStorage;
import duke.tasklist.MyList;
import duke.ui.DukeUserInterface;

public class ListCommand extends Command {

    @Override
    public void execute(MyList taskList, DukeUserInterface ui, DukeStorage storage) {
        ui.printList(taskList);
    }
}
