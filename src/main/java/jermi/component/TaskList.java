package jermi.component;

import static jermi.misc.Constant.TASK_LIST_START_INDEX;

import jermi.exception.InvalidIndexException;
import jermi.exception.JermiException;
import jermi.task.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class to represent a list of tasks.
 */
public class TaskList {
    /** List of tasks. */
    private List<Task> list;

    /**
     * Public constructor for class.
     * Uses an {@link ArrayList} to store the tasks.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Returns the list containing the tasks.
     *
     * @return List of tasks.
     */
    public List<Task> getList() {
        return this.list;
    }

    /**
     * Adds task to the list.
     *
     * @param task Task.
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Returns the task of the specified index in the list.
     *
     * @param index Index (starting from 1) of task.
     * @return Task of the specified index.
     * @throws JermiException {@link InvalidIndexException}.
     */
    public Task getTask(int index) throws JermiException {
        try {
            return this.list.get(index - TASK_LIST_START_INDEX);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Removes the task of the specified index in the list.
     *
     * @param index Index (starting from 1) of task.
     * @throws JermiException {@link InvalidIndexException}.
     */
    public void remove(int index) throws JermiException {
        try {
            this.list.remove(index - TASK_LIST_START_INDEX);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }

    }

    /**
     * Removes all tasks from the list.
     */
    public void clear() {
        this.list.clear();
    }

    /**
     * Returns a list of task descriptions containing one or more of the keywords.
     *
     * @param keywords Keywords used for finding.
     * @return A list of task descriptions containing one or more of the keywords.
     */
    public List<String> find(String... keywords) {
        return this.list
                .stream()
                .filter(task -> task.contains(keywords))
                .map(Task::toString)
                .collect(Collectors.toList());
    }
}
