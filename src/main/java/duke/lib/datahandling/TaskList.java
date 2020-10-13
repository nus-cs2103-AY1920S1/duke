package duke.lib.datahandling;

import duke.lib.common.DukeException;
import duke.lib.task.Task;

import java.util.ArrayList;

/**
 * Contains the list of tasks with operations to add, delete, etc.
 */
public class TaskList {
    private ArrayList<Task> textStorage;

    /**
     * Default constructor for TaskList.
     */
    public TaskList() {
        textStorage = new ArrayList<Task>();
    }

    /**
     * Stores the given task in the list.
     *
     * @param task the task to be stored.
     */
    public void store(Task task) {
        assert task != null;
        textStorage.add(task);
    }

    /**
     * Stores the given list of task in the list.
     *
     * @param tasks the list of task to be stored.
     */
    public void store(ArrayList<Task> tasks) {
        for (Task task : tasks) {
            textStorage.add(task);
        }
    }

    /**
     * Returns the size of the list.
     *
     * @return int size.
     */
    public int getSize() {
        return textStorage.size();
    }

    /**
     * Marks the task as completed.
     *
     * @param index the index of the task in the list.
     * @return the task back after modification.
     * @throws DukeException thrown if the task cannot be found.
     */
    public Task markAsDone(int index) throws DukeException {
        --index;
        if (index < 0 | index >= textStorage.size()) {
            throw new DukeException("There's no Task attached to that number");
        }
        return textStorage.get(index).completed();
    }

    /**
     * Deletes the given task from the list.
     *
     * @param index the index of the task in the list.
     * @return the task that was deleted.
     * @throws DukeException thrown if the task cannot be found.
     */
    public Task delete(int index) throws DukeException {
        --index;
        if (index < 0 | index >= textStorage.size()) {
            throw new DukeException("There's no Task attached to that number");
        }
        return textStorage.remove(index);
    }

    /**
     * Returns the list attached to the Tasklist in an ArrayList.
     *
     * @return the list to be returned.
     */
    public ArrayList<Task> getList() {
        assert textStorage != null;
        return textStorage;
    }
}
