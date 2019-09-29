/**
 * This class represents a specific command of listing tasks stored in Duke.
 */

package duke.commands;

import duke.managers.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;

public class ListCommand extends Command {

    public ListCommand() {
    }

    /**
     * Prints all the tasks that is stored in Duke at that point in time.
     *
     * @param tasks contains the data structure of Tasks stored in Duke
     * @param ui contains methods dealing with interaction with the user
     * @param storage contains methods to load and save information in the file
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String printedLines  = "Here are the tasks in your list:" + "\n";
        int maxNum = tasks.totalNumTasks();
        for (int i = 1; i <= maxNum; i++) {
            printedLines += i + "." + tasks.getTask(i) + "\n";
        }
        return ui.printLine(printedLines.trim());
    }

    public boolean isExit() {
        return false;
    }
}
