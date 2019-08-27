import java.util.ArrayList;

/**
 * Represents a list of Tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Creates a new TaskList.
     * @param list The list of tasks from Storage.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Add Task into the TaskList.
     * @param t The task to be added.
     */
    public void add(Task t) {
        list.add(t);
    }

    /**
     * Delete Task from the TaskList.
     * @param index The index of the task.
     * @return The task that is deleted.
     */
    public Task delete(int index) {
        Task t = list.remove(index);
        return t;
    }

    public int size() {
        return list.size();
    }

    /**
     * Get Task of given index from TaskList.
     * @param index The index of Task.
     * @return The Task of given index.
     */
    public Task get(int index) {
        return list.get(index);
    }
}
