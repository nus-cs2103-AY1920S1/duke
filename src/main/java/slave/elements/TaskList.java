package slave.elements;

import slave.exception.DukeException;

import slave.task.Task;

import java.util.ArrayList;

/**
 * Class which contains an list of tasks and methods to add/remove/set
 */
public class TaskList {

    private ArrayList<Task> taskList;
    private Storage storage;

    /**
     * Constructor to initialise TaskList
     * @param storage Storage class where data is stored
     */
    public TaskList(Storage storage) {
        this.taskList = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Constructor to initialise TaskList with a given list
     * @param list list to initialise with
     * @param storage Storage class where data is stored
     */
    public TaskList(ArrayList<Task> list, Storage storage) {
        this.taskList = list;
        this.storage = storage;
    }

    /**
     * Adds task to list
     * @param task Task to be added
     * @throws DukeException throws when there's error in adding task to storage
     */
    public void addToList(Task task) throws DukeException {
        this.taskList.add(task);
        this.storage.addTask(task);
    }

    /**
     * Getter method for list
     * @return An arraylist of tasks
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Get task from list by index
     * @param index index of task to be retrieved
     * @return Task of the corresponding index
     */
    public Task getTaskByIndex(int index) {
        return this.taskList.get(index - 1);
    }

    /**
     * Removes task from list and storage
     * @param index index of task to be removed
     * @throws DukeException Throws when there is error in removing task from storage
     */
    public void removeFromList(int index) throws DukeException {
        this.taskList.remove(index - 1);
        if (this.getSize() == index - 1){
            this.storage.refreshStorage(taskList);
            return;
        }
        reorderIndex(index);
        this.storage.refreshStorage(taskList);
    }

    /**
     * Sets task of input index to done
     * @param index Task to be set as done
     * @throws DukeException throws when there is error in setting task done in storage
     */
    public void setDoneInList(int index) throws DukeException {
        this.taskList.get(index - 1).setDone();
        this.storage.refreshStorage(taskList);
    }

    /**
     * Returns the number of tasks in current list
     * @return number of tasks in current list
     */
    public int getSize(){
        return this.taskList.size();
    }

    /**
     * Clears and wipes list and storage of tasks
     * @throws DukeException throws if storage cannot be wiped
     */
    public void clearList() throws DukeException {
        this.taskList.clear();
        this.storage.clearStorage();
    }

    /**
     * Reorders index of tasks accordingly after deletion/removal, done by decrementing by 1
     * @param index index to start reordering
     */
    private void reorderIndex(int index) {
        if (this.taskList.size() == 0){
            return;
        }
        for (int i = index - 1; i < this.getSize(); i++){
            taskList.get(i).decrementId();
        }
    }
}
