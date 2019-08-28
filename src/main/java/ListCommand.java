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
     * Displays the task list.
     * @param tasks a TaskList that stores the list of tasks
     * @param ui a TaskList that stores the list of tasks
     * @param storage Storage object to save task changes to text file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        ui.showTaskList(taskList);
    }
}
