package duke.command;

import duke.task.Task;
import java.util.ArrayList;

/**
 * Maintains an ArrayList of <code>Task</code>s that keeps track of all Tasks available at a given time.
 */
public class TaskList {
    private ArrayList<Task> arr;

    /**
     * Constructs a taskList object.
     * @param arrayList ArrayList that the represents the TaskList.
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
     * @return ArrayList of Tasks
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
     * @param task task to be appended to the TaskList
     * @return boolean indicating if the given task in argument is unique to the TaskList
     */
    public boolean add(Task task) {
        if (arr.contains(task)) {
            return false;
        } else {
            arr.add(task);
            return true;
        }
    }

    /**
     * Finds Tasks that have the input string in their descriptions.
     * @param filter the String that determines whether or not Task will be included in the output.
     * @return TaskList containing subset of Tasks that contain filter in their description.
     */
    public TaskList find(String filter) {
        ArrayList<Task> tempArray = new ArrayList<>();
        for (Task tempTask : arr) {
            if (tempTask.getDescription().contains(filter)) {
                tempArray.add(tempTask);
            }
        }
        return new TaskList(tempArray);
    }

    /**
     * Determines if there are no tasks in the TaskList.
     * @return boolean representing if there are any tasks in the TaskList.
     */
    public boolean isEmpty() {
        return arr.isEmpty();
    }
}
