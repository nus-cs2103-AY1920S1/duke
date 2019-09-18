package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a task list that would be populated by task objects.
 *
 * @see Task
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor that takes in a array list of tasks.
     *
     * @param tasks Arraylist of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the list of tasks.
     *
     * @return list of task
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the task at the index.
     *
     * @return task
     */
    public Task getTask(int number) {
        return tasks.get(number);
    }

    /**
     * Gets the size of the task list.
     *
     * @return the size of the task list
     */
    public int getTasksSize() {
        return tasks.size();
    }

    /**
     * Adds a task into list of task.
     *
     * @param task task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Sets task at number to be done in task list.
     *
     * @param number number for task to be deleted
     */
    public void done(int number) {
        Task task = tasks.get(number);
        task.setDone();
    }

    /**
     * Deletes duke.task.Task at number in task list.
     *
     * @param number number for task to be deleted
     */
    public void delete(int number) {
        Task task = tasks.get(number);
        tasks.remove(number);
    }
}
