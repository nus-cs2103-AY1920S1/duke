package command;

import utilities.ExpenseList;
import utilities.Storage;
import utilities.TaskList;
import utilities.Ui;

public class DeleteExpenseCommand extends Command {
    public DeleteExpenseCommand(String command) {
        super(command);
    }

    @Override
    public String executeAsString(TaskList tasks, Ui ui, Storage storage, ExpenseList expenses) throws Exception {
        String[]splitWords = command.split(" ");

        try {
            int val = Integer.parseInt(splitWords[1]);
            assert val <= (expenses.size()) : "Enter a smaller number";
            String result = ui.deleteExpenseMessage(val - 1, expenses);
            expenses.remove(val - 1);
            storage.updateFile(tasks, expenses);
            return result;
        } catch (AssertionError f) {
            return f.getMessage();
        } catch (Exception e) {
            return "File not found";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
