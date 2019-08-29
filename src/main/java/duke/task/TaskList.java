package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;

/**
 * Represents the list of completed and uncompleted tasks.
 */
public class TaskList {

    /**
     * Represents the list of tasks.
     */
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    /**
     * This method adds the <code>task</code> into the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * This method removes the <code>task</code> from the task list.
     *
     * @param index The zero-based index of the task to be deleted.
     * @return The task that was removed in a string format.
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    public String delete(int index) throws DukeException {
        try {
            return this.tasks.remove(index).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * This method returns the number of tasks currently in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * This method marks the specified task in the task list as done.
     *
     * @param taskNumber The task to be marked as done.
     */
    public void markAsDone(int taskNumber) {
        this.tasks.get(taskNumber).markedAsDone();
    }

    /**
     * This method retrieves all the task from the task list.
     *
     * @return The task lists.
     */
    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }
}