package duke.command;

/**
 * ListCommands represents the user command to display the task list
 */

import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

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
     * @return String that shows all the tasks in the task list
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        return ui.showTaskList(taskList);
    }
}
