package seedu.duke;

public class EListCommand extends Command {
    private String command;

    /**
     * Class constructor.
     *
     * @param command String command.
     */
    public EListCommand(String command) {
        this.command = command;
    }


    /**
     * Executes the command and checks exceptions.
     * Also, prints out what has been done
     *
     * @param tasks  TaskList of all tasks currently.
     * @param expenses ExpenseList of all expenses currently.
     * @param ui Ui that interacts with user by checking for exceptions and printing out
     *           executed tasks.
     * @param taskStorage Storage that load/write or append to data file after updating tasks.
     * @param expenseStorage Storage that load/write or append to data file after updating expenses.
     */
    public String execute(TaskList tasks, ExpenseList expenses, Ui ui, Storage taskStorage,
                          Storage expenseStorage, Storage incomeStorage) {
        return ui.printAllExpenses(expenses);
    }

    /**
     * Returns false to not exit.
     *
     * @return False
     */
    public boolean isExit() {
        return false;
    }

}
