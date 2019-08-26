package duke.command;

import duke.exception.DukeException;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        super.isExit = true;
    }

    @Override
    public void execute(Tasklist list, Ui ui) throws DukeException {
        ui.showFarewell();
    }
}
