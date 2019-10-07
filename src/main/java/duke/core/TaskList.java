package duke.core;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of <code>Task</code> that can perform operations such as 
 * add and delete on the tasks.
 */
public class TaskList {

    /**
     * An internal <code>ArrayList</code> structure to store and manipulate
     * the tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructs a new <code>TaskList</code> with a default empty list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Overloaded constructor that constructs a new <code>TaskList</code> from
     * a given <code>ArrayList</code> of <code>Task</code>.
     *
     * @param tasks An existing <code>ArrayList</code> of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the <code>ArrayList</code> of tasks associated with this
     * <code>TaskList</code>.
     *
     * @return The <code>ArrayList</code> of tasks associated with this
     * <code>TaskList</code>.
     */
    ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Returns the <code>Task</code> in the list with the given index.
     *
     * @param i The index of the <code>Task</code>.
     * @return The <code>Task</code> in the list with this specific index.
     * @throws DukeException If the task id is larger than the size of the task list.
     */
    public Task getTask(int i) throws DukeException {
        assert i >= 1 : "Task ID starts from 1";
        try {
            return tasks.get(i - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The task does not exist :-(");
        }
    }

    /**
     * Adds a <code>Task</code> to the list.
     *
     * @param t The <code>Task</code> to be added to the list.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Removes the <code>Task</code> with the given index from the list.
     *
     * @param i The index of the <code>Task</code> to be deleted.
     */
    public void removeTask(int i) {
        assert i >= 1 : "Task ID starts from 1";
        tasks.remove(i - 1);
    }

    /**
     * Updates the description or time of an existing task.
     *
     * @param i The index of the task to be updated.
     * @param attribute The attribute to be updated (description or time).
     * @param newValue The new value to replace the existing one.
     * @throws DukeException If the task does not exist, or does not have a time attribute.
     */
    public void updateTask(int i, String attribute, String newValue) throws DukeException {
        assert i >= 1 : "Task ID starts from 1";
        Task t = this.getTask(i);
        if (attribute.equals("description")) {
            t.updateDescription(newValue);
        } else if (attribute.equals("time")) {
            if (t instanceof Deadline) {
                ((Deadline) t).updateDueTime(Parser.formatDateTimeForDeadline(newValue));
            } else if (t instanceof Event) {
                ((Event) t).updateOccurTime(Parser.formatDateTimeForEvent(newValue));
            } else {
                throw new DukeException("OOPS!!! Normal tasks do not have a time attribute.");
            }
        }
    }

    /**
     * Returns the number of tasks stored in the list.
     *
     * @return An integer representing the number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }
}




