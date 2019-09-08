package duke.task;

import duke.storage.Storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    /** ArrayList of tasks including ToDos, Deadlines, Events. */
    private ArrayList<Task> list;

    /**
     * Creates an instance of an empty TaskList.
     */
    public TaskList() {
        list = new ArrayList<>() {
            private Comparator<Task> comparator = (t1, t2) -> {
                LocalDateTime d1 = t1.getDate();
                LocalDateTime d2 = t2.getDate();
                if (d1 == null && d2 == null) {
                    return 0;
                } else if (d1 == null) {
                    return -1;
                } else if (d2 == null) {
                    return 1;
                } else {
                    return d1.compareTo(d2);
                }
            };

            @Override
            public boolean add(Task task) {
                boolean result = super.add(task);
                Collections.sort(this, comparator);
                return result;
            }
        };
    }

    /**
     * Creates an instance of a TaskList.
     * Loads tasks stored on data file.
     *
     * @param storage Storage object to access data file.
     */
    public TaskList(Storage storage) {
        this();
        load(storage);
    }

    /**
     * Loads the tasks from storage file into TaskList object.
     *
     * @param storage Storage instance to load files.
     */
    public void load(Storage storage) {
        list.clear();
        storage.parseFile()
                .forEach(task -> {
                    list.add(task);
                    Task.addToTotalTasks();
                });
    }

    /**
     * Gets the total number of tasks in this list.
     *
     * @return Total number of tasks in the list.
     */
    public int getTotalTasks() {
        return list.size();
    }

    /**
     * Adds Task to list.
     *
     * @param task Task to be added.
     * @return Task that was added.
     */
    public Task addTask(Task task) {
        list.add(task);
        return task;
    }

    /**
     * Does Task at the given 1-based index.
     *
     * @param index Index of task to check (1-based).
     * @return Task that was done.
     */
    public Task doTask(int index) {
        Task task = getTask(index);
        task.markAsDone();
        assert task.isDone() : "task marked for done is not done.";
        return task;
    }

    /**
     * Deletes a task at given 1-based index to delete.
     *
     * @param index Index of the task to delete (1-based).
     * @return Task that was deleted. Null if index is out of range.
     */
    public Task deleteTask(int index) {
        if (index > list.size()) {
            return null;
        }
        Task task = getTask(index);
        list.remove(index - 1);
        return task;
    }

    /**
     * Gets a Task from the list.
     *
     * @param index index of task to get (1-based).
     * @return Task object.
     */
    public Task getTask(int index) {
        return list.get(index - 1);
    }

    /**
     * Gets a copy of ArrayList of all tasks in the task list.
     *
     * @return ArrayList of all tasks in the task list.
     */
    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> newList = new ArrayList<>(list);
        assert newList.size() == list.size() : "immutable copy has error.";
        return newList;
    }

    private TaskList filterByCondition(Function<Task, Boolean> f) {
        TaskList taskList = new TaskList();
        list.stream()
                .filter(task -> f.apply(task))
                .forEach(taskList::addTask);
        return taskList;
    }

    /**
     * Filters out all tasks, whose description contains the substring,
     * and returns a new task list with those tasks.
     *
     * @param substring Substring that is to be tested against all tasks' description.
     * @return Returns a new TaskList with tasks' descriptions contains the substring.
     */
    public TaskList filterByString(String substring) {
        return filterByCondition(task -> task.getDescription().contains(substring));
    }

    public TaskList filterByToDos() {
        return filterByCondition(task -> task instanceof ToDo);
    }

    public TaskList filterByDeadlines() {
        return filterByCondition(task -> task instanceof Deadline);
    }

    public TaskList filterByEvents() {
        return filterByCondition(task -> task instanceof Event);
    }

    /**
     * Returns String representation of the task list.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        String indent = String.format("%5s", "");
        StringBuilder strb = new StringBuilder(indent + "Here are the tasks in your list:\n");
        IntStream.range(0, list.size())
                .mapToObj(index -> {
                    return index < list.size() - 1
                            ? String.format("%s%d.%s\n", indent, index + 1, list.get(index))
                            : String.format("%s%d.%s", indent, index + 1, list.get(index));
                })
                .forEach(strb::append);
        return strb.toString();
    }
}
