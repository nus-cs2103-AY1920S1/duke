package command;

import main.Storage;
import main.TaskList;
import task.Task;

import java.util.ArrayList;

/**
 * A Command to print a list of all the Tasks in the TaskList.
 */
public class ListCommand implements Command {
    public ListCommand() { }

    /**
     * Prints into the console all the Tasks in the TaskList.
     *
     * @param taskList The TaskList from the main Duke object.
     * @param storage The Storage from the main Duke object.
     */
    public void execute(TaskList taskList, Storage storage) {
        ArrayList<Task> list = taskList.getTaskList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                System.out.println(i + 1 + "." + list.get(i));
            }
        }
    }
}
