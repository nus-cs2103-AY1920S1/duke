package command;

import utilities.ExpenseList;
import utilities.Storage;
import utilities.TaskList;
import utilities.Ui;

public class HelloCommand extends Command {
    public HelloCommand(String command) {
        super(command);
    }

    @Override
    public String executeAsString(TaskList tasks, Ui ui, Storage storage, ExpenseList expenses) throws Exception {
        return ui.showWelcomeFX();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
