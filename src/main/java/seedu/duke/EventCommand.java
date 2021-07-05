package seedu.duke;

/**
 * Represents the command when user inputs 'event'. An <code>EventCommand</code> object
 * can <code>execute</code> the command with checks for exception by adding event to tasks.
 */
public class EventCommand extends Command {
    private String command;

    /**
     * Class constructor.
     *
     * @param command String command of 'event'.
     */
    public EventCommand(String command) {
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
                          Storage expenseStorage, Storage incomeStorage) throws Exception {
        Parser.checkErrorForEventCommand(command, tasks, ui);
        tasks.add(Parser.createEvent(command));
        if (tasks.size() > 1) {
            taskStorage.appendFile(tasks);
        } else {
            taskStorage.writeFile(tasks);
        }
        assert tasks.size() > 0 : "tasks size invalid";
        return ui.printAddedTask(tasks.get(tasks.size() - 1))
                + "\n"
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
        return "EventCommand";
    }
}