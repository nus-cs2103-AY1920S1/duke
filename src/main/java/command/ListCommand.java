package command;

import main.Storage;
import main.TaskList;
import task.Task;

import java.util.ArrayList;

/**
 * A Command to print a list of all the Tasks in the TaskList.
 */
public class ListCommand implements Command {
    public ListCommand() {

    }

    /**
     * Prints into the console all the Tasks in the TaskList.
     *
     * @param taskList The TaskList from the main Duke object.
     * @param storage The Storage from the main Duke object.
     */
    public String execute(TaskList taskList, Storage storage) {
        StringBuilder toReturn = new StringBuilder();
        ArrayList<Task> list = taskList.getTaskList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                toReturn.append(i + 1);
                toReturn.append(".");
                toReturn.append(list.get(i));
                toReturn.append("\n");
            }
        }
        return toReturn.toString();
    }
}
