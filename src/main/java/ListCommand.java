/**
 * ListCommands represents the user command to display the task list
 */

import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand() {
        super();
        isExit = false;
    }

    /**
     * Displays the task list
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        ui.showTaskList(taskList);
    }
}
