package duke;

import duke.task.Task;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Encapsulates a task list.
 */
public class TaskList {
    protected List<Task> taskList;

    /**
     * Constructs a TaskList object with saved data on hard disk.
     *
     * @param taskList  Task list data retrieved from hard disk.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructs a TaskList object with no prior data.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Lists tasks in task list.
     *
     * @return Tasks in task list as a string.
     */
    public String list() {
        StringBuilder tasks = new StringBuilder();
        String task;
        int index = 1;
        for (Iterator iterator = taskList.iterator(); iterator.hasNext(); index++) {
            assert (index >= 1);
            task = index + "." + iterator.next() + "\n";
            tasks.append(task);
        }
        assert (tasks.length() >= 0);
        return tasks.toString();
    }

    /**
     * Retrieves task at a given index.
     *
     * @param index  Index of task to be retrieved.
     * @return Task at given index.
     */
    public Task get(int index) {
        assert (index <= taskList.size());
        return taskList.get(index);
    }

    /**
     * Removes task at a given index.
     *
     * @param index  Index of task to be removed.
     * @return Task at given index.
     */
    public Task remove(int index) {
        assert (index <= taskList.size());
        return taskList.remove(index);
    }

    /**
     * Returns size of task list.
     *
     * @return Size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Adds task to task list.
     *
     * @param task  Task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
        assert (taskList.contains(task));
    }

    /**
     * Retrieves list of tasks containing keyword.
     *
     * @param keyword  Keyword to be searched for.
     * @return List of tasks containing keyword as a String.
     */
    public String find(String keyword) {
        StringBuilder tasks = new StringBuilder();
        String task;
        int i = 1;
        int index = 1;
        for (Iterator iterator = taskList.iterator(); iterator.hasNext(); i++) {
            assert (i >= 1 && index >= 1);
            Task current = (Task) iterator.next();
            if (current.containsKeyword(keyword)) {
                task = index + "." + current + "\n";
                tasks.append(task);
                index++;
            }
        }
        return tasks.toString();
    }

    /**
     * Returns iterator of task list.
     *
     * @return Iterator of the task list.
     */
    public Iterator iterator() {
        return taskList.iterator();
    }
}
