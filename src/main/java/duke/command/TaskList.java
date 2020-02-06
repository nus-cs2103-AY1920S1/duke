package duke.command;

import duke.task.Task;
import duke.task.TaskComparator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the list of task that has and has not been done.
 */
public class TaskList {
    protected ArrayList<Task> list;

    /**
     * Constructs a TaskList object that takes in an ArrayList of Task.
     * @param list List of tasks that has been done and to be done.
     * @throws FileNotFoundException if file from which the list of task is
     *     loaded cannot be found.
     */
    public TaskList(ArrayList<Task> list) throws FileNotFoundException {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Adds a task into the list of tasks.
     * @param task Task that is to be added to the list.
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Deletes a task from the list.
     * @param index the index at which the task is located in the ArrayList.
     * @return The task that has been removed from the list.
     */
    public Task deleteTask(int index) {
        return this.list.remove(index);
    }

    /**
     * Gets the ArrayList of Task.
     * @return the ArrayList of type Task.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Gets the task at the specified index.
     * @param index The index of the task in the ArrayList.
     * @return the Task that is retrieved from the list.
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Sorts the list of tasks.
     */
    public void sortTaskList() {
        Collections.sort(this.list, new TaskComparator());
    }
}

