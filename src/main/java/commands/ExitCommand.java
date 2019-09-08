package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

public class ExitCommand extends Command {

    private boolean isExit = true;

    @Override
    public boolean isExit() {
        return this.isExit;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(ui.closingStatement());
    }
}
