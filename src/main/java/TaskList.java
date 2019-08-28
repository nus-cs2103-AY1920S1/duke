import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores the tasks of the user.
 */
public class TaskList implements Serializable {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns the number of tasks present in the task list.
     *
     * @return The number of tasks present in the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the task present in the specific index of the task list.
     *
     * @param index The target index.
     * @return The task present in the specific index of the task list.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Marks as complete the task present in the specific index of the task list.
     *
     * @param index The target index.
     */
    public void markTaskAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    /**
     * Deletes the task present in the specific index of the task list and returns it.
     *
     * @param index The target index.
     * @return The deleted task.
     * @throws OutOfBoundsDeletionException If the target index is not present in the task list.
     */
    public Task deleteTask(int index) throws OutOfBoundsDeletionException {
        try {
            return taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfBoundsDeletionException("No task with index number " + (index + 1) + " on your tasklist!");
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param newTask The new task to be added.
     */
    public void addTask(Task newTask) {
        taskList.add(newTask);
    }
}
