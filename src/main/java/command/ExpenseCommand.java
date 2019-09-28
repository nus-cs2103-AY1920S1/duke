package command;

import expense.RandomExpense;
import utilities.ExpenseList;
import utilities.Storage;
import utilities.TaskList;
import utilities.Ui;

public class ExpenseCommand extends Command {

    public ExpenseCommand(String command) {
        super(command);
    }

    @Override
    public String executeAsString(TaskList tasks, Ui ui, Storage storage, ExpenseList expenses) {
        try {
            String mainCommand = command.substring(6);
            RandomExpense item = new RandomExpense(mainCommand);
            expenses.add(item);
            storage.updateFile(tasks, expenses);
            return ui.expenseMessage(item);
        } catch (Exception e) {
            return "Enter proper format";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
