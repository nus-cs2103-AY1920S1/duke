import tasks.Task;
import java.util.ArrayList;

/**
 * Contains the task list and updates tasks in the list.
 *
 * @author Michelle Yong
 */
public class TaskList {
    ArrayList<Task> list = new ArrayList<Task>();

    public TaskList() {}

    /**
     * Creates the task list.
     *
     * @param list The list of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Gets the size of the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Adds new task to the task list.
     *
     * @param t The task to be added to the task list.
     */
    public void addTask(Task t) {
        list.add(t);
    }

    /**
     * Marks task of index n in the list as done.
     *
     * @param n The index of the task to be marked as done in the list.
     * @return The task after it is marked as done.
     */
    public Task markTaskAsDone(int n) {
        assert (list.size() > n);
        Task task = list.get(n);
        task.markAsDone();
        return task;
    }

    /**
     * Sets task with priority level.
     *
     * @param n Task Number.
     * @param priority Priority Level - easy, medium or hard.
     * @return The task with the updated priority level.
     */
    public Task setTaskPriority(int n, String priority) {
        assert (list.size() > n);
        Task task = list.get(n);
        task.setPriority(priority);
        return task;
    }

    /**
     * Removes task of index n from the list.
     *
     * @param n The index of the task to be removed from the list.
     * @return The task after it is removed from the list.
     */
    public Task removeTask(int n) {
        assert (list.size() > n);
        return list.remove(n);
    }

    /**
     * Clears the taskList.
     */
    public void clear() {
        list.clear();
    }

    /**
     * Shows the list of all tasks in the task list.
     */
    public String showList() {
        int length = list.size();
        StringBuffer listDetails =
                new StringBuffer("Here are the tasks in your list:\n");
        for (int i = 1; i <= length; i++) {
            Task task = list.get(i - 1);
            listDetails.append(i);
            listDetails.append(". ");
            listDetails.append(task);
            listDetails.append("\n");
        }
        return listDetails.toString();
    }

    /**
     * Shows the list of tasks found by keyword in the task list.
     *
     * @param taskList The list of tasks found by the keyword.
     * @return The list of tasks found.
     */
    public String showTaskFound(ArrayList<Task> taskList) {
        int length = taskList.size();
        StringBuffer taskFound =
                new StringBuffer("Here are the matching tasks in your list:\n");
        for (int i = 1; i <= length; i++) {
            Task task = taskList.get(i - 1);
            taskFound.append(i);
            taskFound.append(". ");
            taskFound.append(task);
            taskFound.append("\n");
        }
        return taskFound.toString();
    }
}