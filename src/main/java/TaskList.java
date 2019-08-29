import java.util.ArrayList;

public class TaskList {
    /**
     * Stores list of Task
     */
    private ArrayList<Task> tasks;

    /**
     * Constructor that takes in a Arraylist of tasks
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the list of tasks
     * @return list of task
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Catches error when list is called with 0 items in Arraylist of tasks
     * @throws IllegalArgumentException
     */
    public void list() throws IllegalArgumentException {
        if (tasks.size() == 0) {
            throw new IllegalArgumentException("Nothing found in list");
        }
    }

    /**
     * Adds a task into list of task
     * @param task task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }
    /**
     * Sets task at number to be done in task list
     * @throws IllegalArgumentException catches negative numbers input other arguments.
     */
    public void done(int number) throws IndexOutOfBoundsException {
        if (number > tasks.size() || number <= 0) {
            throw new IndexOutOfBoundsException("The task number does not exist.");
        }
        Task task = tasks.get(number - 1);
        task.setDone();
    }

    /**
     * Deletes Task at number in task list
     * @param number number shown
     * @throws IndexOutOfBoundsException
     */
    public void delete(int number) throws IndexOutOfBoundsException {
        if (number > tasks.size() || number <= 0) {
            throw new IndexOutOfBoundsException("The task number does not exist.");
        }
        Task task = tasks.get(number - 1);
        tasks.remove(number - 1);
    }
}
