import java.util.ArrayList;

/**
 * Represents a list of Tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a new TaskList.
     * @param tasks The list of tasks from Storage.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Add Task into the TaskList.
     * @param t The task to be added.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Delete Task from the TaskList.
     * @param index The index of the task.
     * @return The task that is deleted.
     */
    public Task delete(int index) {
        Task t = tasks.remove(index);
        return t;
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Get Task of given index from TaskList.
     * @param index The index of Task.
     * @return The Task of given index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }
}
