package task;

import java.util.ArrayList;

/**
 * Represents a task list
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initiliaze an empty task list
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * @param tasks is the task list
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
