package duke.handler;

import duke.task.Task;

import java.util.ArrayList;

/**
 * The TaskList contains the list of current tasks.
 */
public class TaskList {
    ArrayList<Task> list = new ArrayList<>();

    /**
     * Contructs a TaskList instance with a pre-existing list of tasks.
     * @param list The list of tasks that already existed.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Constructs a TaskList instance with no tasks added to the list yet.
     */
    public TaskList() {
    }

    /**
     * A getter for the list of tasks.
     * @return The list of tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }
}
