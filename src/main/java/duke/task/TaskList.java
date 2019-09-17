package duke.task;

import duke.exception.DukeException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.lang.IndexOutOfBoundsException;


/**
 * Class that encapsulates a list of Tasks. Contains various methods
 * to manipulate the list.
 *
 * @see Task
 * @see Event
 * @see Deadline
 * @see Todo
 */
public class TaskList implements Serializable {
    private List<Task> tasks; // List of all tasks

    /**
     * Returns a TaskList object with an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    /**
     * Returns a TaskList object initialized with Task objects found
     * in a List of Task objects.
     * @param tasks Existing List object containing Task objects.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a TaskList object initialized with Task objects found
     * in a TaskList object.
     *
     * @param taskList Existing TaskList object containing Task objects.
     */
    public TaskList(TaskList taskList) {
        this.tasks = taskList.tasks;
    }

    /**
     * Deletes the task corresponding to the given taskIndex from the stored list
     * in the TaskList object.
     *
     * @param taskIndex index of task to be deleted. Uses 1-indexing.
     * @throws DukeException if index is invalid or refers to a non-existent task.
     **/
    public Task delete(int taskIndex) throws DukeException {
        try {
            taskIndex--; // convert to zero-indexing
            return this.tasks.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Hey! There's no such task!");
        }
    }

    /**
     * Returns the task corresponding to the given taskIndex from the stored list
     * in the TaskList object.
     *
     * @param taskIndex index of task to be deleted. Uses 1-indexing.
     * @return Task object corresponding to taskIndex
     * @throws DukeException if index is invalid or refers to a non-existent task.
     **/
    public Task at(int taskIndex) throws DukeException {
        try {
            taskIndex--; // convert to zero-indexing
            return this.tasks.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Hey! There's no such task!");
        }
    }

    /**
     * Adds a task to the list stored in the TaskList object.
     * @param task Task object to be added to list
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the current number of Task objects in the TaskList object.
     * @return integer representing the number of Tasks in the TaskList object.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns a TaskList containing subset of Task objects matching
     * the provided searchWord. This method is case-insensitive.
     *
     * @param searchWord String to search for
     * @return TaskList containing Tasks that match searchWord
     */
    public TaskList getTaskSubsetMatching(String searchWord) {
        List<Task> resultList = new ArrayList<>();
        this.tasks.forEach((task) -> {
            if (task.getDescription().toLowerCase()
                    .contains(searchWord.toLowerCase())) {
                resultList.add(task);
            }
        });
        return new TaskList(resultList);
    }

    /**
     * Replaces the current list of tasks in the TaskList object with
     * a list of tasks from another TaskList object. Note that this
     * is implemented as a shallow copy operation (internally, only the
     * references are copied over)
     *
     * @param newList the TaskList object to be copied over to this object
     */
    public void replaceWith(TaskList newList) {
        this.tasks = newList.tasks;
    }

    /**
     * Returns a string representation of the TaskList object. The output will
     * be formatted with one Task on each line, prepended by their respective
     * indexes (1-indexed).
     *
     * @return String representation of the TaskList object
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task task : this.tasks) {
            sb.append(String.format("%3d. ", i++));
            sb.append(task);
            sb.append("\n");
        }
        return sb.toString();
    }
}
