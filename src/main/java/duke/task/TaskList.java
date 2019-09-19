package duke.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks inputted by the user.
 */
public class TaskList {

    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a particular task with the requested index from this list of tasks.
     *
     * @param index task number.
     * @return task with the requested index.
     */
    public static Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the total number of tasks in this list.
     *
     * @return total number of tasks
     */
    public static int size() {
        return tasks.size();
    }

    /**
     * Deletes a particular task with the requested index from this list of tasks.
     *
     * @param index task number.
     */
    public static void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task
     */
    public static void add(Task task) {
        tasks.add(task);
    }

}
