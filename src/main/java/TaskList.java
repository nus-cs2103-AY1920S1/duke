import java.util.ArrayList;

/**
 * TaskList class. Instances represents lists of texts stored by user.
 */
public class TaskList {
    /** ArrayList to store Tasks. */
    private ArrayList<Task> list;

    /**
     * Constructor.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Adds Task to list.
     * @param task Task to be added.
     * @return Task that was added.
     */
    public Task addTask(Task task) {
        list.add(task);
        return task;
    }

    /**
     * Does Task at the given 1-based index.
     * @param index Index of task to check (1-based).
     * @return Task that was done.
     */
    public Task doTask(int index) {
        Task task = getTask(index);
        task.markAsDone();
        return task;
    }

    /**
     * Deletes a task at given 1-based index to delete.
     * @param index Index of the task to delete (1-based).
     * @return Task that was deleted.
     */
    public Task deleteTask(int index) {
        Task task = getTask(index);
        list.remove(index - 1);
        return task;
    }

    /**
     * Gets a Task from the list.
     * @param index index of task to get (1-based).
     * @return Task object.
     */
    public Task getTask(int index) {
        return list.get(index - 1);
    }

    /**
     * String representation of list (indented).
     * @return String representation.
     */
    @Override
    public String toString() {
        String indent = String.format("%5s", "");
        StringBuilder strb = new StringBuilder(indent + "Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); ++i) {
            strb.append(indent).append(i + 1).append(".").append(list.get(i));
            if (i < list.size() - 1) {
                strb.append("\n");
            }
        }
        return strb.toString();
    }
}
