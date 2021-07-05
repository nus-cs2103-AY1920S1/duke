package seedu.duke;

import java.io.IOException;

public class DeleteAllCommand extends Command {
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
     */
    public String execute(TaskList tasks, ExpenseList expenses, Ui ui, Storage taskStorage,
                          Storage expenseStorage, Storage incomeStorage) throws Exception {
        expenses.deleteAll();
        expenseStorage.removeFile();
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
