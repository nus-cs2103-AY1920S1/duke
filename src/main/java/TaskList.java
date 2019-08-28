import java.util.ArrayList;

/**
 * Handles the entire list of tasks
 */
public class TaskList {

    protected ArrayList<Task> listOfTask;

    public TaskList() {
        this.listOfTask = new ArrayList<Task>();
    }

    /**
     * adding of tasks to the list
     * @param task the task to be added
     */
    public void add(Task task) {
        listOfTask.add(task);
    }

    /**
     * getting the total number of tasks
     * @return size of the list
     */
    public int size() {
        return listOfTask.size();
    }

    /**
     * getting the task at a specific task number
     * @param index the task number
     * @return the task at that task number
     */
    public Task get(int index) {
        return listOfTask.get(index);
    }

    /**
     * deleting the task from the list
     * @param index the task number
     */
    public void remove(int index) {
        listOfTask.remove(index);
    }
}
