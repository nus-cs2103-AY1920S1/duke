package bot.duke.task;

import java.util.ArrayList;

public class TaskList {
    /**
     * ArrayList of Tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructs the TaskList object from existing list of Task objects.
     *
     * @param tasks Existing list of Task objects
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs the TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Returns list of Task objects.
     *
     * @return List of the Task objects
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the Task at the specified index.
     *
     * @param chosenTaskNo Specified index
     * @return Chosen Task object
     */
    public Task getTask(int chosenTaskNo) {
        return this.tasks.get(chosenTaskNo - 1);
    }

    /**
     * Adds the Task object to the list.
     *
     * @param task Task object
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Sets the Task object at the specified index as done.
     *
     * @param chosenTaskNo Specified index
     * @return Chosen Task object
     */
    public Task doDoneTask(int chosenTaskNo) {
        Task doneTask = this.tasks.get(chosenTaskNo - 1);
        assert doneTask != null;
        doneTask.setDone(true);
        return doneTask;
    }

    /**
     * Deletes the Task object at the specified index.
     *
     * @param chosenTaskNo Specified index
     * @return Deleted Task object
     */
    public Task doDeleteTask(int chosenTaskNo) {
        Task deletedTask = this.tasks.get(chosenTaskNo - 1);
        assert deletedTask != null;
        this.tasks.remove(chosenTaskNo - 1);
        return deletedTask;
    }

}
