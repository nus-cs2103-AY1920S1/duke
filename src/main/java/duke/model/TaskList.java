package duke.model;

import duke.model.task.Task;

import java.util.ArrayList;

/**
 * A class representing the list of tasks to be done.
 */
public class TaskList {
    public ArrayList<Task> list;

    /**
     * Constructor for TaskList, in the event when the list is provided.
     *
     * @param list The list of tasks to be done.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Another constructor for TaskList, in the event when local data is not found.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Add a task to the list in this class.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Delete the task in the list based on the index parameter.
     *
     * @param index The index of the task in the list to be deleted.
     * @return Returns the deleted task.
     */
    public Task delete(int index) {
        return list.remove(index);
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void setList(ArrayList<Task> list) {
        this.list = list;
    }
}
