package seedu.duke;

/**
 * Represents the command when user inputs 'delete'. A <code>DeleteCommand</code> object
 * can <code>execute</code> the command with checks for exception by deleting task from list.
 */
public class DeleteCommand extends Command {
    private String command;

    /**
     * Class constructor.
     *
     * @param command String command of 'delete'.
     */
    public DeleteCommand(String command) {
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
     * @throws DukeException  If there is incorrect user input format.
     * @throws java.io.IOException If there is problems reading/writing or appending to file.
     * @throws Exception If there is problems with Parser reading in file line.
     */
    public String execute(TaskList tasks, ExpenseList expenses, Ui ui, Storage taskStorage,
                          Storage expenseStorage,  Storage incomeStorage) throws Exception {
        Parser.checkErrorForDeleteCommand(command, tasks, ui);
        int curr = Parser.taskToDelete(command);
        assert curr > 0 : "Task num is not valid";
        Task deletedTask = tasks.get(curr - 1);
        tasks.remove(curr - 1);
        taskStorage.writeFile(tasks);
        return ui.printDeletedTaskMsg(deletedTask) + "\n"
                + ui.printNoOfTaskInList(tasks);
    }

    /**
     * Returns false to not exit.
     *
     * @return False
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns type of command.
     *
     * @return String of command type.
     */
    @Override
    public String toString() {
        //for testing purposes
        return "DeleteCommand";
    }
}
