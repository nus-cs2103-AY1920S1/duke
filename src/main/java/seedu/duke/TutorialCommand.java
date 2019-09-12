package seedu.duke;

/**
 * Represents the command when user inputs 'tutorial'. A <code>TutorialCommand</code> object
 * can <code>execute</code> the command.
 **/
public class TutorialCommand extends Command {

    /**
     * Class constructor.
     */
    public TutorialCommand() {

    }

    /**
     * Returns the string tutorial to run tutorial scene.
     *
     * @param tasks  TaskList of all tasks currently.
     * @param expenses ExpenseList of all expenses currently.
     * @param ui Ui that interacts with user by checking for exceptions and printing out
     *           executed tasks.
     * @param taskStorage Storage that load/write or append to data file after updating tasks.
     * @param expenseStorage Storage that load/write or append to data file after updating expenses.
     * @return
     */
    public String execute(TaskList tasks, ExpenseList expenses, Ui ui, Storage taskStorage, Storage expenseStorage) {
        return "tutorial";
    }

    /**
     * Returns the boolean false to not exit duke.
     *
     * @return false to not exit duke.
     */
    public boolean isExit() {
        return false;
    }
}
