package duke.command;

import duke.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showGoodbye();
    }
}
