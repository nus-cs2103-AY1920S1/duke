import java.util.ArrayList;
import java.util.List;

/**
 * Contains the task list.
 */
public class TaskList {
    private List<Task> listOfTasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     */
    public TaskList(List<Task> taskList) {
        this.listOfTasks = taskList;
    }

    /**
     * Method to remove task from task list.
     *
     * @param index of the task
     * @throws DukeException if error
     */
    public Task removeTask(int index) throws DukeException {
        if (index < 0 || index >= listOfTasks.size()) {
            throw new DukeException("Invalid index");
        }
        return listOfTasks.remove(index);
    }

    /**
     * Method to add a task.
     *
     * @param task to be added
     */
    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    /**
     * Method to get the list of tasks.
     *
     * @return A list of tasks
     */
    public List<Task> getListOfTasks() {
        return listOfTasks;
    }

    /**
     * Method to get size of the list of tasks.
     *
     * @return An int representing the size
     */
    public int getSize() {
        return listOfTasks.size();
    }
}
