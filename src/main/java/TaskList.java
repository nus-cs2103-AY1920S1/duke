import java.util.ArrayList;

/**
 * Represents the list of Tasks in a Duke object.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Creates a TaskList object that stores the list of tasks of a Duke object in an ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber);
    }

    public void deleteTask(int taskNumber) {
        this.tasks.remove(taskNumber);
    }

    /**
     * Marks the task at the given index in the ArrayList as done.
     *
     * @param taskNumber index of the task in the ArrayList.
     */
    public void markAsDone(int taskNumber) {
        this.tasks.get(taskNumber).markAsDone();
    }

    public int taskListSize() {
        return this.tasks.size();
    }

}
