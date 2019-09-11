package duke.command;

import duke.data.Storage;
import duke.object.TaskList;
import duke.user.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
