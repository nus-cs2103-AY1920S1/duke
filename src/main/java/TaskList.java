import java.util.ArrayList;

/**
 * Represents a list that contains all currently saved tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Constructs a new TaskList based on a given ArrayList.
     *
     * @param tasks an arraylist of tasks to be used as the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Returns an arrayList that contains all stores tasks.
     *
     * @return An ArrayList of stored tasks.
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Removes a task with the given index from the TaskList.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public void remove(int taskIndex) {
        taskList.remove(taskIndex);
    }

    /**
     * Gives the number of tasks that are currently Stored.
     *
     * @return the number of tasks currently stored.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Retreves the task at the given index.
     *
     * @param index the index of the task to be retrieved.
     * @return The task at the given index.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Completes the task at the given index.
     *
     * @param index The index of the task to be completed.
     */
    public void complete(int index) {
        taskList.get(index).complete();
    }

    /**
     * Adds a new task to the taskList.
     *
     * @param task The new task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }
}
