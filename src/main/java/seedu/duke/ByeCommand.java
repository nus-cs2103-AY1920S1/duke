package seedu.duke;

/**
 * Represents the command when user inputs 'bye'. A <code>ByeCommand</code> object
 * can <code>execute</code> the command and end Duke.
 */
public class ByeCommand extends Command {
    private String command;
    private Ui ui;

    /**
     * Class constructor.
     *
     * @param command String command of "bye".
     */
    public ByeCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the command.
     *
     * @param tasks TaskList currently.
     * @param expenses ExpenseList currently.
     * @param ui Ui initialized in <code>Duke</code> to interact with user.
     * @param taskStorage Storage to write/load/append to data file after updating tasks.
     * @param expenseStorage Storage to write/load/append to data file aftering updating expenses.
     * @return String of goodbye message.
     */
    public String execute(TaskList tasks, ExpenseList expenses, Ui ui, Storage taskStorage,
                          Storage expenseStorage, Storage incomeStorage) {
        this.ui = ui;
        return ui.printGoodbyeMsg();
    }

    /**
     * Returns true to exit.
     * Prints goodbye message before returning.
     *
     * @return True to end Duke
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Returns type of command.
     *
     * @return String of command type.
     */
    @Override
    public String toString() {
        //for testing purposes
        return "ByeCommand";
    }
}
