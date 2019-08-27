import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Returns number of tasks.
     * @return Number of tasks.
     */
    public int numberOfTasks() {
        return this.tasks.size();
    }

    /**
     * Delete task at index.
     * @param index Task index to be deleted, zero-indexed.
     */
    public void deleteTaskAt(int index) {
        this.tasks.remove(index);
    }

    /**
     * Add task.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }
}
