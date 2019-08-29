package duke.data;

import duke.data.tasks.Task;
import java.util.ArrayList;

/**
 * Implements a list for storing tasks.
 * @author Lim Yong Shen, Kevin
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds the specified task to the task list.
     * @param task The specified task.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns true if this TaskList is empty.
     * @return true if this TaskList is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the task at the specified index of the task list.
     * @param index The specified index.
     * @return The task at the specified index of the task list.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes and returns the task at the specified index of the task list.
     * @param index The specified index.
     * @return The task at the specified index of the task list.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the size of this TaskList.
     * @return The size of this TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a sub task list containing the specified keyword.
     * @param keyword The specified keyword.
     * @return A sub task list containing the specified keyword.
     */
    public TaskList subTaskListContainingKeyWord(String keyword) {
        TaskList subTaskList = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                subTaskList.add(task);
            }
        }
        return subTaskList;
    }

}
