package tasks;

import java.util.ArrayList;

/**
 * Class that represents the tasklist that holds all the tasks in Duke.
 */
public class TaskList {

    private ArrayList<Task> addedItems;

    /**
     * Empty Constructor for the tasklist.
     */
    public TaskList() {
        addedItems = new ArrayList<>();
    }

    /**
     * Constructs the tasklist.
     * The tasklist will be initialized with all the tasks in the given list.
     *
     * @param ls tasklist to be initialized with
     */
    public TaskList(ArrayList<Task> ls) {
        this.addedItems = ls;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return int of the number of tasks in the list
     */
    public int getNumOfTask() {
        return addedItems.size();
    }

    /**
     * Returns a task given its index.
     *
     * @param idx index of the task to be returned
     * @return the task represented by its index
     */
    public Task getTaskByIndex(int idx) {
        return addedItems.get(idx);
    }

    /**
     * Returns the tasklist in an ArrayList.
     *
     * @return ArrayList of the tasks
     */
    public ArrayList<Task> getTaskList() {
        return addedItems;
    }

    /**
     * Deletes a tast in the tasklist.
     *
     * @param idx index of the task to be deleted
     */
    public void deleteTaskByIdx(int idx) {
        addedItems.remove(idx);
    }

    /**
     * Adds a task into the list.
     *
     * @param tk task to be added into the list
     */
    public void addTask(Task tk) {
        addedItems.add(tk);
    }

}
