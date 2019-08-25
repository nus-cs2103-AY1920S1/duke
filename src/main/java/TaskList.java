import java.util.ArrayList;

/**
 * TaskList class. Instances represents lists of texts stored by user.
 */
public class TaskList {
    /** ArrayList to store Tasks. */
    private ArrayList<Task> list;

    /**
     * Constructor. Creates a new empty tasklist.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Constructor.
     * @param storage
     */
    public TaskList(Storage storage) {
        this();
        load(storage);
    }

    /**
     * Loads the tasks from storage.
     * @param storage Storage instance to load files.
     */
    public void load(Storage storage) {
        ArrayList<Task> tasks = storage.parseFile();
        list.clear();
        tasks.forEach(task -> list.add(task));
    }

    /**
     * Gets the total number of tasks in this list.
     * @return Total number of tasks in the list.
     */
    public int getTotalTasks() {
        return list.size();
    }

    /**
     * Adds Task to list.
     * @param task Task to be added.
     * @return Task that was added.
     */
    public Task addTask(Task task) {
        list.add(task);
        return task;
    }

    /**
     * Does Task at the given 1-based index.
     * @param index Index of task to check (1-based).
     * @return Task that was done.
     */
    public Task doTask(int index) {
        Task task = getTask(index);
        task.markAsDone();
        return task;
    }

    /**
     * Deletes a task at given 1-based index to delete.
     * @param index Index of the task to delete (1-based).
     * @return Task that was deleted. Null if index is out of range.
     */
    public Task deleteTask(int index) {
        if (index > list.size()) {
            return null;
        }
        Task task = getTask(index);
        list.remove(index - 1);
        return task;
    }

    /**
     * Gets a Task from the list.
     * @param index index of task to get (1-based).
     * @return Task object.
     */
    public Task getTask(int index) {
        return list.get(index - 1);
    }

    /**
     * Gets an arraylist of all tasks in the tasklist.
     * @return ArrayList of all tasks in the tasklist.
     */
    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> newList = new ArrayList<>();
        list.forEach(x -> newList.add(x));
        return newList;
    }

    /**
     * String representation of list (indented).
     * @return String representation.
     */
    @Override
    public String toString() {
        String indent = String.format("%5s", "");
        StringBuilder strb = new StringBuilder(indent + "Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); ++i) {
            strb.append(indent).append(i + 1).append(".").append(list.get(i));
            if (i < list.size() - 1) {
                strb.append("\n");
            }
        }
        return strb.toString();
    }
}
