import java.util.LinkedList;
import java.util.ListIterator;

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
        assert !(taskNum < 0) : "Negative task number";
        taskList.remove(taskNum -1);
    }

    public void addTask(Task task) {
        assert task != null;
        taskList.add(task);
    }

    public LinkedList<Task> getList() {
        return taskList;
    }

    public Task getTask(int taskNum) {
        assert !(taskNum < 0) : "Negative task number";
        return taskList.get(taskNum -1);
    }

    public int size() {
        return taskList.size();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Searches for the target String in every task's description.
     * Loops through the list of tasks tries to match the target String
     * a substring in the task description.
     * @param target Target string to search for
     * @return LinkedList<Task> of matching tasks
     */
    public LinkedList<Task> searchFor(String target) {
        ListIterator<Task> iter = taskList.listIterator();
        LinkedList<Task> tasksFound = new LinkedList<>();
        Task current;

        while (iter.hasNext()) {
            current = iter.next();
            String description = current.getDesc();

            if (description.contains(target)) {
                tasksFound.add(current);
            }
        }

        return tasksFound;

    }
}