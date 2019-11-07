package duke.tasks;

import java.io.Serializable;

import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Add a task to the task list.
     * @param task the task to be added into the task list
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Get a task from the task list by index.
     * @param index the index of the task in the task list
     * @return the task of the given index
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Remove a task from the task list by index.
     * @param index the index of the task in the task list to be removed
     */
    public void removeTask(int index) {
        taskList.remove(index);
    }

    /**
     * Get length of the task list.
     * @return the length of the task list
     */
    public int getSize() {
        return taskList.size();
    }
}
