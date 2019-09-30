package command;

import main.Storage;
import main.TaskList;
import main.UI;
import task.Task;

import java.util.ArrayList;

public class UndoCommand implements Command {

    public UndoCommand() {

    }

    /**
     * Executes the UndoCommand which deletes the last command.
     *
     * <p></p>Taking the TaskList and Storage object of the main Duke class as arguments, this command will find the
     * last Task added to the TaskList and delete it.
     *
     * @param taskList The TaskList from the main Duke object.
     * @param storage The Storage from the main Duke object.
     */
    public String execute(TaskList taskList, Storage storage) {
        ArrayList<Task> taskListArray = taskList.getTaskList();
        Task task = taskListArray.remove(taskListArray.size() - 1);

        storage.writeToFile(taskListArray);

        return UI.undoStart(task);
    }
}
