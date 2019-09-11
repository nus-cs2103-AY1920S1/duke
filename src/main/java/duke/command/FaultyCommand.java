package duke.command;

import duke.data.Storage;
import duke.exception.DukeException;
import duke.object.TaskList;
import duke.user.Ui;

public class FaultyCommand extends Command {
    private DukeException e;

    public FaultyCommand(DukeException e) {
        this.e = e;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(e);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
