package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        super.isExit = true;
    }

    @Override
    public void execute(Tasklist list, Ui ui, Storage storage) throws DukeException {
        storage.store(list.tasks);
        ui.showFarewell();
    }
}
