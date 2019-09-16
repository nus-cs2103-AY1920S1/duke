package execution;

import models.Task;

import java.util.ArrayList;

/**
 * Represents a tasklist that will keep track of the tasks that the user enters.
 * It consists of methods that will add, delete and mark complete tasks in the current list.
 */
public class TaskList {

    private ArrayList<Task> list;

    /**
     * Creates a tasklist object by creating a new empty arraylist.
     */
    public TaskList() {

        this.list = new ArrayList<>();
    }

    /**
     * Creates a tasklist object with an existing list of tasks.
     *
     * @param currentList of existing tasks reloaded from the duke.txt file.
     */
    public TaskList(ArrayList<Task> currentList) {

        this.list = currentList;
    }

    public int getSize() {

        return this.list.size();
    }

    /**
     * Returns the current list.
     *
     * @return the existing ArrayList.
     */
    public ArrayList<Task> getList() {

        return this.list;

    }

    /**
     * Returns a task in the arraylist at the input integer's position.
     *
     * @param index is the position of the file in the arraylist.
     * @return the Task at that position.
     */

    public Task getTaskByIndex(int index) {

        return this.list.get(index);
    }

    /**
     * Adds a task to the list attribute of this execution.TaskList object.
     *
     * @param current the models.Task object to be added to the list.
     */
    public void addTask(Task current) {

        this.list.add(current);

    }

    /**
     * Deletes a task off the list attribute of this execution.TaskList object.
     *
     * @param current the models.Task object to be deleted off the list.
     */
    public Task deleteTask(int current) {

        Task deleted = list.get(current);
        list.remove(current);

        return deleted;

    }

}
