package command;

import task.TaskList;

/**The RefreshCommand class.
 * 1) Deletes all tasks
 *
 */
public class RefreshCommand extends Command {

    /**
     * Deletes all tasks from the taskList.
     *
     * @param reference is the tasklist being used by the program
     * @return String the formatted output, after running through formatOutput()
     */
    @Override
    public String executeCommand(TaskList reference) {
        this.reference = reference;
        reference.refreshTasks();
        return this.formatOutput();
    }

    /**
     * Returns the formatted command as a formatted string.
     *
     *@return String formatted
     */

    public String formatOutput() {
        return TextFormatter.refreshFormat();
    }


}
