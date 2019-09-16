package trackr.tasklist;

import trackr.task.Task;
import java.util.ArrayList;

/**
 * Represents the list of tasks to be completed. A <code>TaskList</code> contains an arraylist of
 * tasks and supports retrieval, addition and deletion operations.
 */
public class TaskList {

    /**
     * Contains all tasks to be completed.
     */
    private ArrayList<Task> tasks;

    /**
     * Default constructor when user has no tasks stored from previous session.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Class constructor that is called when user has tasks stored from previous session.
     * @param storage Arraylist of tasks retrieved from storage on hard disk.
     */
    public TaskList(ArrayList<Task> storage) {
        this.tasks = storage;
    }

    /**
     * Returns the number of tasks on the list.
     * @return int This returns the number of tasks on the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * This method retrieves a task using index of the task in the list.
     * @param i Index of task
     * @return Task This returns the task corresponding to index provided
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * This method adds a task to the bottom of the list.
     * @param t Task to be added
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * This method removes a task in the list according to the index provided.
     * @param i Index of task
     */
    public void remove(int i) {
        tasks.remove(i);
    }

    public void overrideTasks(ArrayList<Task> newTasks) {
        this.tasks = new ArrayList<>(newTasks);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
