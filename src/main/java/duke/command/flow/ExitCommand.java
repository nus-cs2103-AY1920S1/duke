package duke.command.flow;

import duke.command.Command;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printExitMsg();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
