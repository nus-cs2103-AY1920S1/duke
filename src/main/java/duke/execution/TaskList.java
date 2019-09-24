package duke.execution;

import duke.models.Task;

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

    /**
     * Returns the size of the current arraylist.
     *
     * @return the integer value of the number of tasks in the arraylist currently.
     */
    public int getSize() {

        assert this.list != null;
        return this.list.size();

    }

    /**
     * Returns the current list.
     *
     * @return the existing ArrayList.
     */
    public ArrayList<Task> getList() {

        assert this.list != null;
        return this.list;

    }

    /**
     * Returns a task in the arraylist at the input integer's position.
     *
     * @param index is the position of the file in the arraylist.
     * @return the Task at that position.
     */

    public Task getTaskByIndex(int index) {

        assert this.list != null;
        return this.list.get(index);

    }

    /**
     * Adds a task to the list attribute of this duke.execution.TaskList object.
     *
     * @param current the duke.models.Task object to be added to the list.
     */
    public void addTask(Task current) {

        assert this.list != null;
        this.list.add(current);

    }

    /**
     * Adds a task to the top of the list, since it is of priority.
     *
     * @param priority task to be added.
     */
    public void addPriorityTask(Task priority) {

        this.list.add(0, priority);

    }

    /**
     * Deletes a task off the list attribute of this duke.execution.TaskList object.
     *
     * @param current the duke.models.Task object to be deleted off the list.
     */
    public Task deleteTask(int current) {

        Task deleted = list.get(current);
        list.remove(current);
        assert this.list != null;

        return deleted;

    }

}
