package kappa.elements;

import kappa.exception.KappaException;

import kappa.task.Task;

import java.util.ArrayList;

/**
 * Class which contains an list of tasks and methods to add/remove/set.
 */
public class TaskList {

    private ArrayList<Task> taskList;
    private Storage storage;

    /**
     * Constructor to initialise TaskList.
     *
     * @param storage Storage class where data is stored.
     */
    public TaskList(Storage storage) {
        this.taskList = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Constructor to initialise TaskList with a given list.
     *
     * @param list List to initialise with.
     * @param storage Storage class where data is stored.
     */
    public TaskList(ArrayList<Task> list, Storage storage) {
        this.taskList = list;
        this.storage = storage;
    }

    /**
     * Adds task to list.
     *
     * @param task Task to be added.
     * @throws KappaException Throws when there's error in adding task to storage.
     */
    public void addToList(Task task) throws KappaException {
        this.taskList.add(task);
        this.storage.addTask(task);
    }

    /**
     * Gets current list of tasks.
     *
     * @return Current ArrayList of tasks.
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Gets task from list by index.
     *
     * @param index Index of task to be retrieved.
     * @return Task of the corresponding index.
     */
    public Task getTaskByIndex(int index) {
        return this.taskList.get(index - 1);
    }

    /**
     * Removes task from list and storage.
     *
     * @param index Index of task to be removed.
     * @throws KappaException Throws when there is error in removing task from storage.
     */
    public void removeFromList(int index) throws KappaException {
        this.taskList.remove(index - 1);
        if (this.getSize() == index - 1) {
            this.storage.refreshStorage(taskList);
            return;
        }
        reorderIndex(index);
        this.storage.refreshStorage(taskList);
    }

    /**
     * Sets task of input index to done.
     *
     * @param index Task to be set as done.
     * @throws KappaException Throws when there is error in setting task done in storage.
     */
    public void setDoneInList(int index) throws KappaException {
        this.taskList.get(index - 1).setDone();
        this.storage.refreshStorage(taskList);
    }

    /**
     * Returns the number of tasks in current list.
     *
     * @return Number of tasks in current list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Clears and wipes list and storage of tasks.
     *
     * @throws KappaException Throws if storage cannot be wiped.
     */
    public void clearList() throws KappaException {
        this.taskList.clear();
        this.storage.clearStorage();
    }

    /**
     * Reorders index of tasks accordingly after deletion/removal, done by decrementing by 1.
     *
     * @param index Index to start reordering.
     */
    private void reorderIndex(int index) {
        if (this.taskList.size() == 0) {
            return;
        }
        for (int i = index - 1; i < this.getSize(); i++) {
            taskList.get(i).decrementId();
        }
    }
}
