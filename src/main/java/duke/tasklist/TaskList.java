package duke.tasklist;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents a TaskList of Tasks that Duke currently holds.
 */
public class TaskList {
    private List<Task> list;

    /**
     * Constructor of TaskList.
     *
     * @param list Initial list of tasks, loaded from storage file or otherwise.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Alternate constructor if initial list of tasks is not satisfied.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    public FilteredList<Task> getTasks() {
        return new FilteredList<>((ObservableList<Task>) this.list);
    }

    /**
     * Method to add a new task to the TaskList.
     *
     * @param t Task to be added.
     */
    public void add(Task t) {
        list.add(t);
    }

    /**
     * Method to check if TaskList is empty.
     *
     * @return true if empty, false if otherwise.
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Method to remove Task of given index from TaskList.
     *
     * @param index given index to be removed.
     * @return Task that is removed.
     */
    public Task remove(int index) {
        return this.list.remove(index - 1);
    }

    /*
     * Returns current size of TaskList.
     *
     * @return current size
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Returns Task t at given index.
     *
     * @param index to be queried
     * @return Task at index
     */
    public Task get(int index) {
        return this.list.get(index - 1);
    }

    /**
     * Returns a new TaskList that contains all tasks with name that contains the
     * given string.
     *
     * @param tofind - string to be queried.
     * @return TaskList that contains corresponding tasks.
     */

    public TaskList find(String tofind) {
        List<Task> newList = new ArrayList<>();
        for (Task t : this.list) {
            if (t.getName().contains(tofind)) {
                newList.add(t);
            }
        }
        return new TaskList(newList);
    }

    /**
     * For every task in the TaskList, prints its corresponding string.
     *
     * @return String containing all tasks in the TaskList
     */
    public String printAllTasks() {
        if (this.size() != 0) {
            StringBuilder res = new StringBuilder();
            int count = 1;
            for (Task task : list) {
                res.append("").append(count).append(".").append(task).append("\n");
                count++;
            }
            return res.toString().trim();
        } else {
            return "There are no tasks on your list!";
        }
    }
}
