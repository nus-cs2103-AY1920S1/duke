import java.io.IOException;

public class ExpensesCommand extends Command {

    public ExpensesCommand(String action, String variable) {
        super(action, variable);
    }

    @Override
    public String execute(CompleteList errands, Ui ui, Storage storage) throws IOException {
        assert errands != null;
        assert ui != null;
        assert storage != null;
        Expenses expense = new Expenses(action, variable);
        ExpenseList expenses = new ExpenseList();
        expenses.addToExpensesList(expense);
        expenses.addToCompleteList(expense);
        storage.addToFile(Storage.file, expense.toString());
        String expenseOutput = ui.printGI() + "\n";
        expenseOutput += "  " + expense.toString() + "\n";
        expenseOutput += Ui.printNumOfTasks();
        return expenseOutput;
    }
}
