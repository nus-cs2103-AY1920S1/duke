package duke.command;

import duke.task.Task;

import java.util.ArrayList;

/**
 * The is the class to store the list of tasks.
 */
public class TaskList {

    private static ArrayList<Task> store;

    /**
     * The constructor of the class, assign a list of tasks to the variable.
     *
     * @param store A list of tasks passed by parameter.
     */
    public TaskList(ArrayList<Task> store) {
        TaskList.store = store;
    }

    /**
     * Another constructor of the class, instantiate a new empty list of tasks.
     */
    public TaskList() {
        store = new ArrayList<>();
    }

    /**
     * To get the list of tasks.
     *
     * @return The list of tasks.
     */
    static ArrayList<Task> getList() {
        return store;
    }
}
