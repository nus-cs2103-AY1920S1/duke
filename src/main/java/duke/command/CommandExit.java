package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class CommandExit extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
