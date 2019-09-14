package seedu.duke;

import java.io.IOException;

public class IncomeCommand extends Command {
    private String command;

    public IncomeCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the command and checks exceptions.
     * Also, prints out what has been done.
     *
     * @param tasks          TaskList of all tasks currently.
     * @param expenses       ExpenseList of all expenses currently.
     * @param ui             Ui that interacts with user by checking for exceptions and printing out
     *                       executed tasks.
     * @param taskStorage    Storage that load/write or append to data file after updating tasks.
     * @param expenseStorage Storage that load/write or append to data file after updating expenses.
     * @throws DukeException If there is incorrect user input format.
     * @throws IOException   If there is problems reading/writing or appending to file.
     * @throws Exception     If there is problems with Parser reading in file line.
     */
    public String execute(TaskList tasks, ExpenseList expenses, Ui ui, Storage taskStorage,
                          Storage expenseStorage, Storage incomeStorage) throws Exception {
        Parser.checkNoIncomeInputError(command);
        double income = Parser.parseAndCheckErrorsForIncome(command);
        expenses.inputIncome(income);
        incomeStorage.writeIncomeFile(income);
        return ui.printNoOfExpenseInList(expenses);
    }

    /**
     * Returns if should exit.
     *
     * @return Boolean if duke should end.
     */
    public boolean isExit() {
        return false;
    }
}
