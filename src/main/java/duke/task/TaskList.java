package duke.task;

import duke.exception.DukeException;
import duke.types.SortType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TaskList {
    protected List<Task> tasks;

    /**
     * Initiate a new TaskList.
     *
     * @param tasks Optional List of Tasks can be passed to the constructor to initialise TaskList with tasks.
     */
    public TaskList(List<Task>... tasks) {
        if (tasks.length == 1) {
            this.tasks = tasks[0];
        } else {
            this.tasks = new ArrayList<>();
        }
    }

    /**
     * Getter for list of tasks.
     *
     * @return List of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Getter for size variable.
     *
     * @return Size of task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns if TaskList is empty.
     *
     * @return true if no task in list, true if there are tasks in list
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns index of a task if task is in list.
     * @param t Task
     * @return Index of task
     * @throws DukeException If task not in taskList
     */
    public int indexOf(Task t) throws DukeException {
        if (this.getTasks().contains(t)) {
            return tasks.indexOf(t);
        } else {
            throw new DukeException("Task not in taskList.");
        }
    }

    /**
     * Get Task from list at index.
     *
     * @param index Index of task in list
     * @return Task at index
     * @throws DukeException If index is out of range of list
     */
    public Task get(int index) throws DukeException {
        if (isValidIndex(index)) {
            return tasks.get(index - 1);
        } else {
            throw new DukeException("OOPS!!! There is no task at index " + index + ".");
        }
    }

    /**
     * Add task to list.
     *
     * @param t Task to be added to list
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Remove task from task list.
     *
     * @param t Task to be removed
     * @throws DukeException If task is not in task list
     */
    public void remove(Task t) throws DukeException {
        if (tasks.contains(t)) {
            tasks.remove(t);
        } else {
            throw new DukeException("OOPS!!! There is no such task in taskList.");
        }
    }

    /**
     * Remove task at index from list.
     *
     * @param index Index at which to remove task
     * @return Removed task
     * @throws DukeException If index is out of range of list
     */
    public Task remove(int index) throws DukeException {
        Task task;
        if (isValidIndex(index)) {
            task = tasks.remove(index - 1);
        } else {
            throw new DukeException("OOPS!!! There is no such task in taskList.");
        }
        return task;
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }

    /**
     * Find Tasks by description keywords.
     *
     * @param keyword Keyword string that is used to search for tasks.
     * @return List of tasks that matches the keyword passed in.
     */
    public List<Task> findByKeyword(String keyword) {
        List<Task> tasks = new ArrayList<>(
                getTasks().stream()
                        .filter(t -> t.getDesc().contains(keyword))
                        .collect(Collectors.toList())
        );
        return tasks;
    }

    /**
     * Clear TaskList and remove all tasks.
     */
    public void clear() {
        getTasks().clear();
    }

    /**
     * Sort the list of tasks by a certain criteria.
     *
     * @param sortType Type of data by which user want to sort tasks.
     * @throws DukeException If sort type passed in is not supported (ie. not sortable).
     */
    public void sort(SortType sortType) throws DukeException {
        Comparator<Task> comparator;
        Function<Task, String> fn;
        switch (sortType) {
        case DESC:
            fn = Task::getDesc;
            break;
        case TASKTYPE:
            fn = t -> t.getClass().getName();
            break;
        case DONE:
            fn = t -> t.getDoneStatus();
            break;
        default:
            throw new DukeException("Invalid sort type");
        }
        getTasks().sort(Comparator.comparing(fn));
    }
}
