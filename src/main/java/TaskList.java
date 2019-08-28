import java.util.LinkedList;

/**
 * TaskList represents a list of tasks.
 * The class supports the storing of the tasks, and
 * operations that can change or interact with this list of tasks.
 */
public class TaskList {
    private LinkedList<Task> taskList;

    /**
     * Constructs a TaskList object.
     * Initializes the LinkedList of tasks.
     */
    public TaskList() {
        taskList = new LinkedList<Task>();
    }

    /**
     * Constructs a TaskList object.
     * Passes an existing list of tasks to be
     * stored in this TaskList object.
     * @param taskList
     */
    public TaskList(LinkedList<Task> taskList) {
        this.taskList = taskList;
    }

    public void deleteTask(int taskNum) {
        taskList.remove(taskNum -1);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public LinkedList<Task> getList() {
        return taskList;
    }

    public Task getTask(int taskNum) {
        return taskList.get(taskNum -1);
    }

    public int size() {
        return taskList.size();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }
}