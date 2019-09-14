package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * The TaskList class provides an abstraction for list of Tasks with basic append, delete and
 * formatting methods.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs an empty TaskList with default capacity of 10.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList with the given string list.
     *
     * @param inputList ArrayList of Strings
     * @throws DukeException if TaskList cannot be formed from the given list
     */
    public TaskList(ArrayList<String> inputList)
        throws DukeException {
        this.list = new ArrayList<>();
        for (String str : inputList) {
            list.add(TaskFactory.createTaskFromFileFormattedString(str));
        }
    }

    /**
     * Returns the number of elements in this list.
     * 
     * @return the number of elements in this list
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Appends the specified task to the end of this list.
     * 
     * @param t task to be appended to this list
     */
    public void add(Task t) {
        list.add(t);
    }

    /**
     * Marks the task at the specified index in the list as done. The specified index
     * assumes a one-based list (i.e first element of the list is of index 1).
     * 
     * @param index the index of the element to be marked as done
     */
    public void done(int index) {
        Objects.checkIndex(index - 1, list.size());
        list.get(index - 1).markAsDone();
    }

    /**
     * Returns the task at the specified index in the list. The specified index assumes a one-based
     * list (i.e first element of the list is of index 1). The task is returned as a String.
     * 
     * @param index index at which the element is to be obtained
     * @return the String representation of the task at the specified index
     */
    public String get(int index) {
        Objects.checkIndex(index - 1, list.size());
        return list.get(index - 1).toString();
    }

    /**
     * Removes and returns the task at the specified index in the list. The specified index
     * assumes a one-based list (i.e first element of the list of index 1).
     *
     * @param index index at which the element is to be removed
     * @return the task that was removed
     */
    public Task remove(int index) {
        Objects.checkIndex(index - 1, list.size());
        return list.remove(index - 1);
    }

    /**
     * Returns a String ArrayList containing all tasks contained in this TaskList containing
     * the specified String.
     *
     * @param match the string to match to the tasks
     * @return a String ArrayList containing matching tasks
     */
    public ArrayList<String> find(String match) {
        return this.asFormattedList()
            .stream()
            .filter(task -> task.toLowerCase().contains(match.toLowerCase()))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns an String ArrayList containing the formatted form of this Task list.
     * 
     * @return an String ArrayList containing the formatted form of this Task list
     */
    public ArrayList<String> asFormattedList() {
        AtomicInteger i = new AtomicInteger(1);
        return this.list.stream()
            .map(Object::toString)
            .map(task -> String.format("%d. %s", i.getAndIncrement(), task))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns a String ArrayList containing the string form of this Task list to write to file.
     * 
     * @return a String ArrayList containing the string form of this Task list to write to file
     */
    public ArrayList<String> asFileFormattedList() {
        return this.list.stream()
            .map(Task::toFileFormattedString)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    
    /**
     * Returns the String representation of this Task list.
     * 
     * @return the String representation of this Task list
     */
    @Override
    public String toString() {
        AtomicInteger i = new AtomicInteger(1);
        StringBuilder ret = new StringBuilder();
        this.asFormattedList()
            .forEach(task -> {
                ret.append(String.format("%d. %s\n", i.getAndIncrement(), task));
            });
        return ret.toString();
    }
}
