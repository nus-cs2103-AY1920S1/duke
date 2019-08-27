package duke.task;

import duke.exception.DukeIndexOutOfBoundsException;

import java.util.ArrayList;

/**
 * Represents the list of tasks Duke is to keep track of.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
        tasks.add(null); // leave index 0 unused for clarity.
    }

    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Deletes a task.
     *
     * @param idx the index of the task to delete (when listed).
     * @return the deleted task.
     * @throws DukeIndexOutOfBoundsException if the index of the task does not exist.
     */
    public Task delete(int idx) throws DukeIndexOutOfBoundsException {
        if (idx <= 0 || idx >= tasks.size()) {
            throw new DukeIndexOutOfBoundsException(":'( OOPS!!! There's no such task index.");
        }
        return tasks.remove(idx);
    }

    /**
     * Gets a specific task.
     *
     * @param idx the index of the task to get (when listed).
     * @return the task to get.
     * @throws DukeIndexOutOfBoundsException if the index of the task does not exist.
     */
    public Task get(int idx) throws DukeIndexOutOfBoundsException {
        if (idx <= 0 || idx >= tasks.size()) {
            throw new DukeIndexOutOfBoundsException(":'( OOPS!!! There's no such task index.");
        }
        return tasks.get(idx);
    }

    /**
     * Marks a task as complete.
     *
     * @param idx the index of the task to mark as done (when listed).
     * @return the task marked as complete.
     * @throws DukeIndexOutOfBoundsException if the index of the task does not exist.
     */
    public Task markAsDone(int idx) throws DukeIndexOutOfBoundsException {
        if (idx <= 0 || idx >= tasks.size()) {
            throw new DukeIndexOutOfBoundsException(":'( OOPS!!! There's no such task index.");
        }
        Task task = tasks.get(idx);
        task.markAsDone();
        return task;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the number of tasks in the task list.
     */
    public long count() {
        return tasks.size() - 1; // account for 1-indexing.
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
}
