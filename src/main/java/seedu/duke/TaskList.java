package seedu.duke;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> arr;

    public TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }

    public TaskList() {
        this.arr = new ArrayList<>();
    }

    public int size() {
        return arr.size();
    }

    public Task get(int index) {
        return arr.get(index);
    }

    public void remove(int index) {
        (this.arr).remove(index);
    }

    public void add(Task task) {
        (this.arr).add(task);
    }
}
