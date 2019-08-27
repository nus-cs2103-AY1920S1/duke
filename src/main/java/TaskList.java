import java.util.ArrayList;

/**
 * The TaskList class contains the task list and
 * has methods to modify the tasks in the list.
 */
public class TaskList {

    private static ArrayList<Task> tasks;

    /**
     * Creates the TaskList object that contains an ArrayList of tasks.
     *
     * @param tasks An ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * The size of the ArrayList.
     *
     * @return An int representing how many tasks the ArrayList contains
     */
    public static int size() {
        return tasks.size();
    }

    /**
     * Get the ArrayList containing the tasks.
     *
     * @return An ArrayList containing tasks.
     */
    public static ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Add tasks to the ArrayList of tasks.
     *
     * @param t A task that will be added to the list.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Delete tasks from the ArrayList of tasks.
     *
     * @param deletionNumber An int representing the task to be deleted from the list.
     */
    public void delete(int deletionNumber) {
        tasks.remove(deletionNumber - 1);
    }

    /**
     * Mark a task in the ArrayList as done.
     *
     * @param number An int representing the task to be marked as done.
     */
    public void markDone(int number) {
        tasks.get(number - 1).markAsDone();
    }

}