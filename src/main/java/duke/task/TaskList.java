package duke.task;

import duke.exception.DukeException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.lang.IndexOutOfBoundsException;


/**
 * Class that encapsulates a list of Tasks and various methods
 * to manipulate the list.
 * @see {@link Task}, {@link Event}, {@link Deadline}, {@link Todo}
 */
public class TaskList implements Serializable {
    private List<Task> tasks; // List of all tasks

    /**
     * Returns a TaskList object with an empty task list
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    /**
     * Returns a TaskList object initialized with Task objects found
     * in a List<Task> object
     * @param tasks Existing List object containing Task objects.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a TaskList object initialized with Task objects found
     * in a TaskList object
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
     * @throws DukeException if index is invalid or refers to a non-existent task.
     * @return Task object corresponding to taskIndex
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
     * @param task
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

    public TaskList getTaskSubsetMatching(String searchWord) {
        List<Task> resultList = new ArrayList<>();
        this.tasks.forEach((task) -> {
            if (task.getDescription().contains(searchWord)) {
                resultList.add(task);
            }
        });
        return new TaskList(resultList);
    }

    /**
     * Returns a string representation of the TaskList object formatted with one
     * Task on each line, prepended by their respective indexes (1-indexed)
     *
     * @return String representation of the TaskList object
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task task : this.tasks) {
            sb.append(i++);
            sb.append(". ");
            sb.append(task);
            sb.append("\n");
        }
        return sb.toString();
    }
}
