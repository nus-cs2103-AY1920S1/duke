package command;

import command.Command;
import duke.Storage;
import duke.Ui;
import task.TaskList;

public class ExitCommand extends Command {
    public ExitCommand() {
        super.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewellMessage();
        // return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
