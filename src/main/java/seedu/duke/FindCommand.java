package seedu.duke;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Represents the command when user inputs 'find'. A <code>FindCommand</code> object
 * can <code>execute</code> the command, with checks for exception, by finding matching tasks with a single keyword.
 */

public class FindCommand extends Command {
    protected String command;
    protected TaskList matchList;
    protected DateFormat outDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    protected DateFormat outTimeFormat = new SimpleDateFormat("H.mm aa");

    /**
     * Class constructor.
     *
     * @param command String command of 'find'.
     */
    public FindCommand(String command) {
        this.command = command;
        matchList = new TaskList();
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
     */
    public String execute(TaskList tasks, ExpenseList expenses, Ui ui, Storage taskStorage,
                          Storage expenseStorage, Storage incomeStorage) throws DukeException {
        Parser.checkErrorForFindCommand(command, ui);
        String keyword = Parser.getKeyword(command);
        for (int i = 0; i < tasks.size(); i++) {
            addTaskInListIfMatch(tasks, keyword, i);
        }
        return ui.printAllMatchingTasks(matchList);
    }

    /**
     * Returns tasklist of all tasks that match the keyword.
     *
     * @return TaskList of all tasks matching keyword
     */
    public TaskList getMatchList() {
        return this.matchList;
    }

    /**
     * Returns false to not exit.
     *
     * @return False
     */
    public boolean isExit() {
        return false;
    }

    private void addTaskInListIfMatch(TaskList tasks, String keyword, int index) {
        if (tasks.get(index) instanceof Event) {
            Event event = (Event) tasks.get(index);
            if (event.getDescription().contains(keyword)) {
                matchList.add(event);
            } else if (outDateFormat.format(event.getDate()).contains(keyword)) {
                matchList.add(event);
            } else if (outTimeFormat.format(event.getTime()).contains(keyword)) {
                matchList.add(event);
            }
        } else if (tasks.get(index) instanceof Todo) {
            Todo td = (Todo) tasks.get(index);
            if (td.getDescription().contains(keyword)) {
                matchList.add(td);
            }
        } else if (tasks.get(index) instanceof Deadline) {
            Deadline dl = (Deadline) tasks.get(index);
            if (dl.getDescription().contains(keyword)) {
                matchList.add(dl);
            } else if (outDateFormat.format(dl.getDate()).contains(keyword)) {
                matchList.add(dl);
            } else if (outTimeFormat.format(dl.getTime()).contains(keyword)) {
                matchList.add(dl);
            }
        }
    }
}
