package duke.task;

import java.util.ArrayList;

/**
 * A class representing a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Class constructor.
     * Create an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Class constructor specifying tasks in the list.
     * @param tasks a list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the size of the task list.
     * @return the size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task at certain index.
     * Index starts from 1.
     * @param index the index of the task.
     * @return the task at the index.
     */
    public Task getTaskAtIndex(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Sets status of the task at index as done.
     * @param index the index of the task.
     * @return the task at the index.
     * @throws IndexOutOfBoundsException If the index given is out of range.
     */
    public Task setTaskAtIndexDone(int index) throws IndexOutOfBoundsException {
        tasks.get(index - 1).setDone();
        return tasks.get(index - 1);
    }

    /**
     * Removes the task at the index.
     * @param index the index of the task to be removed.
     * @return the task that was removed.
     */
    public Task removeTaskAtIndex(int index) {
        return tasks.remove(index - 1);
    }

    /**
     * Add task to the task list.
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a list of tasks.
     * @return a list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
