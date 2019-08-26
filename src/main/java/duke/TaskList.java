package duke;

import java.util.ArrayList;

/**
 * Represents a TaskList object. A <code>TaskList</code> object corresponds to
 * an object containing the list of tasks.
 */
public class TaskList {
    ArrayList<Task> taskList;

    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    TaskList() {}

    /**
     * Removes task from the task list.
     *
     * @param task Task to be deleted.
     */
    void deleteTask(Task task) {
        taskList.remove(task);
    }

    /**
     * Adds task to the task list.
     *
     * @param task Task to be added.
     */
    void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Gets current size of the task list.
     *
     * @return int Task list size.
     */
    public int getListSize() {
        return taskList.size();
    }

    /**
     * Gets task from corresponding index of the task list.
     *
     * @return Task from corresponding index of the task list.
     */
    Task getTask(int index) {
        return taskList.get(index -1);
    }
}
