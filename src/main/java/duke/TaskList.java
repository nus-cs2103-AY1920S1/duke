package duke;

import java.util.List;
import java.util.ArrayList;
import duke.task.Task;

public class TaskList {
    private List<Task> list;

    /**
     * Empty constructor.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Constuctor.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Adds an item top the list.
     * @param task Task
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Removes an item from the list based on 1-indexed index.
     * @return Task
     */
    public Task remove(int index) {
        return this.list.remove(index - 1);
    }

    /**
     * Gets the element based on 1-indexed index.
     * @return Task
     */
    public Task get(int index) {
        return this.list.get(index - 1);
    }

    /**
     * Gets the size of the list.
     * @returns int the size of the list
     */
    public int getSize() {
        return this.list.size();
    }
}
