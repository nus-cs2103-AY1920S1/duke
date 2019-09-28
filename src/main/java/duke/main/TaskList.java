package duke.main;

import duke.task.Task;
import java.util.ArrayList;

/**
 * Contains the ArrayList of existing tasks, with operations to add or delete tasks from the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a new TaskList object with an ArrayList of existing tasks loaded by the Storage object.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getTasksSize() {
        return tasks.size();
    }
}
