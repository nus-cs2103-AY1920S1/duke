package duke;

import duke.task.Task;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    /**
     * Return all tasks that contains some keyword.
     *
     * @param keyword the keyword to be matched with
     * @return the list of tasks with the keyword
     */
    public TaskList filter(String keyword) {
        TaskList filteredList = new TaskList();
        for (Task task : this) {
            if (task.getDescription().contains(keyword)) {
                filteredList.add(task);
            }
        }
        return filteredList;
    }

}
