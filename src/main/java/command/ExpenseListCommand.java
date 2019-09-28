package command;

import utilities.ExpenseList;
import utilities.Storage;
import utilities.TaskList;
import utilities.Ui;

public class ExpenseListCommand extends Command {

    public ExpenseListCommand(String command) {
        super(command);
    }

    @Override
    public String executeAsString(TaskList tasks, Ui ui, Storage storage, ExpenseList expenses) throws Exception {
        return ui.expenseListMessage(expenses);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
