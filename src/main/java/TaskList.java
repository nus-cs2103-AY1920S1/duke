import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a list of the user's tasks.
 * Provides methods for adding, deleting, and marking as done each task.
 */
public class TaskList {
    ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor for task list. Initialises with an existing list of tasks.
     *
     * @param tasks List of existing tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for empty task list. Assumes no existing tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns size of task list.
     *
     * @return Number of tasks in list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Displays all the tasks in the list.
     */
    public void displayAllTasks() {
        Iterator<Task> iter = tasks.iterator();
        int i = 1;
        while (iter.hasNext()) {
            Task task = iter.next();
            System.out.println(task.toString());
            i++;
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        Ui.outputTaskAdded(task, this);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index Index position of task to be deleted.
     */
    public void deleteTask(int index) {
        Task removedTask = tasks.remove(index - 1);
        Ui.outputTaskRemoved(removedTask, this);
    }

    /**
     * Marks a task in the list as done.
     *
     * @param index Index position of task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        Ui.outputTaskDone(task);
    }
}
