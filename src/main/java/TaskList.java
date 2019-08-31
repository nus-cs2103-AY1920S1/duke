import java.util.ArrayList;

/**
 * Maintains an ArrayList of <code>Task</code>s that keeps track of all Tasks available at a given time.
 */
public class TaskList {
    private ArrayList<Task> arr;

    /**
     * Constructs a taskList object.
     * @param arrayList arrayList that the represents the TaskList.
     */
    public TaskList(ArrayList<Task> arrayList) {
        arr = arrayList;
    }

    /**
     * Constructs a taskList with an empty ArrayList.
     */
    public TaskList() {
        arr = new ArrayList<Task>();
    }

    /**
     * Returns the Tasks that are in Database.
     * @return arrayList of Tasks
     */
    public ArrayList<Task> getArr() {
        return arr;
    }

    /**
     * Returns number of Tasks in Database.
     * @return number of tasks to manage.
     */
    public int size() {
        return arr.size();
    }

    /**
     * Returns the Task at particular index of the TaskList.
     * @param index index of element to be returned.
     * @return element at 'index' of TaskList.
     */
    public Task get(int index) {
        return arr.get(index);
    }

    /**
     * Removes the Task at particular index of TaskList.
     * @param index index of element to be deleted.
     * @return Task that is removed from the TaskList.
     */
    public Task remove(int index) {
        return arr.remove(index);
    }

    /**
     * Appends a task to the TaskList.
     * @param task task to be appended to the TaskList.
     */
    public void add(Task task) {
        arr.add(task);
    }
}
