package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * TaskList class manages the task list and has operations to add/delete/modify tasks in the list.
 *
 * @author scwaterbear
 */
public class TaskList {

    /**
     * List of tasks for the user.
     */
    private List<Task> tasks;

    /**
     * Class constructor that allocates new list of initial capacity 100.
     */
    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    /**
     * Class constructor that loads existing tasks.
     *
     * @param tasks list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a list of tasks which descriptions match the keyword.
     *
     * @param keyword the search term.
     * @return List Found tasks.
     */
    public List<Task> getTasksWithKeywords(String keyword) {
        return tasks.stream().filter(x -> x.hasKeywordsInDescription(keyword)).collect(Collectors.toList());
    }

    /**
     * Adds a task to the task list.
     *
     * @param t task to add.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Modifies task status to done.
     *
     * @param index task identifier.
     * @return Task the task marked as done.
     * @throws IndexOutOfBoundsException If there is no such index in the list.
     */
    public Task setIsDone(int index) throws IndexOutOfBoundsException {
            tasks.get(index).isDone = true;
            return tasks.get(index);
    }

    /**
     * Removes the specified task from the task list.
     *
     * @param index task identifier.
     * @return Task the task removed.
     * @throws IndexOutOfBoundsException If there is no such index in the list.
     */
    public Task removeTask(int index) throws IndexOutOfBoundsException {
            return tasks.remove(index);
    }

    /**
     * Returns all tasks in the task list.
     *
     * @return List list of all tasks.
     */
    public List<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns size of task list.
     *
     * @return int size of task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns elements that have passed the given predicate,
     * retains elements that have failed the given predicate.
     *
     * @param p Predicate to test a given trait.
     * @return List list of discarded elements from TaskList.
     */
    public List<Task> discardByTrait(Predicate<Task> p) {
        List<Task> discarded = tasks.stream().filter(p::test).collect(Collectors.toList());
        tasks = tasks.stream().filter(x -> !p.test(x)).collect(Collectors.toList());
        return  discarded;
    }
}
