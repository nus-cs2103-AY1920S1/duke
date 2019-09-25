package command;

import main.Storage;
import main.TaskList;
import main.UI;
import task.Task;

import java.util.ArrayList;

/**
 * A Command indicate that a Task from the TaskList and Storage has been completed.
 */
public class DoneCommand implements Command {
    /**
     * Indicates which element of the TaskList has been completed.
     */
    private int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks a specific Task from the TaskList and Storage as indicated by the taskNum attribute as completed.
     *
     * @param taskList The TaskList from the main Duke object.
     * @param storage The Storage from the main Duke object.
     */
    public void execute(TaskList taskList, Storage storage) {
        ArrayList<Task> list = taskList.getTaskList();

        Task currTask = list.get(taskNum);
        currTask.setDone();
        UI.done(currTask);

        storage.writeToFile(list);
    }
}
