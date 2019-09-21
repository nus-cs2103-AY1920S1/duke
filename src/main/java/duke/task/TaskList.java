package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Date;

/**
 * The operations of task list.
 */
public class TaskList {
    ArrayList<Task> tasks; //The tasklist.

    /**
     * Initiates the object with empty list.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Initiates the object with tasklist.
     *
     * @param tasks The task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Deletes task from task list.
     *
     * @param n The order should be delete.
     * @return The task be deleted.
     * @throws DukeException When n > tasklist's size.
     */
    public Task deleteTask(int n) throws DukeException {
        if (n > tasks.size()) {
            throw new DukeException(" ☹ OOPS!!! Do not exist that task.");
        }
        Task removeItem = tasks.get(n - 1);
        tasks.remove(n - 1);
        return removeItem;
    }

    /**
     * Adds task to tasklist.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks task in task list as done.
     *
     * @param n The order of task is done.
     * @return The done task.
     * @throws DukeException When n > tasklist's size.
     */
    public Task doneTask(int n) throws DukeException {
        if (n > tasks.size()) {
            throw new DukeException(" ☹ OOPS!!! Do not exist that task.");
        }
        tasks.get(n - 1).markAsDone();
        return tasks.get(n - 1);
    }

    /**
     * Gets the tasklist.
     *
     * @return Task list.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    public Task editTask(int n, Date newTime) throws DukeException {
        if (n > tasks.size()) {
            throw new DukeException(" ☹ OOPS!!! Do not exist that task.");
        }
        Task oldTask = tasks.get(n - 1);
        if (oldTask.type != Type.D) {
            throw new DukeException("☹ OOPS!!! This task cannot be edit.");
        }
        Deadline toBeEditTask = (Deadline) oldTask;
        Task newTask = toBeEditTask.editTime(newTime);
        tasks.set(n - 1, newTask);

        return tasks.get(n - 1);
    }
}
