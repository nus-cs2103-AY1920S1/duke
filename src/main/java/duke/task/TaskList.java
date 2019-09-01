package duke.task;

import java.util.ArrayList;

/**
 * Represents the collection of the tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Initializes the object with an ArrayList of tasks.
     * @param list ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Initializes the object with an empty ArrayList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task get(int index) {
        return list.get(index);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public void remove(Task task) {
        list.remove(task);
    }

    public int size() {
        return list.size();
    }
}
