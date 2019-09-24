package command;

import java.util.ArrayList;

import main.*;
import exception.DeleteException;
import task.Task;

/**
 * A Command to remove a Task from the TaskList and Storage.
 */
public class DeleteCommand implements Command {
    /**
     * Indicates which element of the TaskList needs to be deleted.
     */
    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Deletes a specific Task from the TaskList and Storage as indicated by the taskNum attribute.
     *
     * @param taskList The TaskList from the main Duke object.
     * @param storage The Storage from the main Duke object.
     */
    public void execute(TaskList taskList, Storage storage) {
        try {
            ArrayList<Task> list = taskList.getTaskList();
            if (taskNum > list.size() || taskNum <= 0) {
                throw new DeleteException();
            }
            Task task = list.remove(taskNum - 1);
            int listSize =  list.size();

            UI.removedTask(task, listSize);

            storage.writeToFile(list);
        } catch (DeleteException dE) {
            System.err.println(dE);
        }
    }
}
