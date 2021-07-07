import java.util.ArrayList;

/**
 * Represents a task list that can be populated with task objects.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Gets size of TaskList.
     * @return int.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds task to TaskList object.
     * @param task Task object.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task in TaskList.
     * @param taskNumber task index.
     */
    public void remove(int taskNumber) {
        tasks.remove(taskNumber);
    }

    /**
     * Gets task in TaskList.
     * @param taskNumber
     * @return task index.
     */
    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }
}