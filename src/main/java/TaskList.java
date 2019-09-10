import java.util.ArrayList;

/**
 * Represents a task list that can be populated with task objects.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int taskNumber) {
        tasks.remove(taskNumber);
    }

    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }
}