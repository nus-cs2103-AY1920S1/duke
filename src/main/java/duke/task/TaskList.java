package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A class that holds a list of tasks that may be added to, removed or
 * marked as done. This list is indexed starting from 1.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    
    /**
     * Initializes an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Imports the given ArrayList of Tasks and initializes this instance with them.
     *
     * @param stringTasks A list of tasks in exported format to be imported.
     */
    public TaskList(ArrayList<String> stringTasks) {
        tasks = new ArrayList<>();
        for (String stringTask: stringTasks) {
            String[] fields = stringTask.split("\\|");
            Task t;
            switch (fields[0]) {
            case "T":
                t = new Todo(fields[2]);
                break;
            case "E":
                t = new Event(fields[2], LocalDateTime.parse(fields[3], inputFormatter));
                break;
            case "D":
                t = new Deadline(fields[2], LocalDateTime.parse(fields[3], inputFormatter));
                break;
            default:
                t = new Todo("nothing");
                break;
            }
            if (fields[1].equals("1")) {
                t.markDone();
            }
            tasks.add(t);
        }
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @returns The size of this instance's list, which is zero if and only if
     * said list is empty.
     */
    public int size() {
        return tasks.size();
    }
    
    /**
     * Adds an undone task to the end of the list.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        tasks.add(t);
    }
    
    /**
     * Gets the task at one-indexed position i in the list.
     *
     * <code>get()</code>, <code>markDone()</code> and <code>removeTask()</code>
     * all take an index i starting from 1, raising an exception if that index is invalid.
     *
     * @param i The index of the task to be retrieved, starting from 1.
     * @return The task at position i.
     * @throws IndexOutOfBoundsException If position i is not in the list.
     */
    public Task get(int i) throws IndexOutOfBoundsException {
        return tasks.get(i - 1);
    }
    
    /**
     * Marks the task at index i as done.
     *
     * @see #get(int)
     */
    public void markDone(int i) throws IndexOutOfBoundsException {
        tasks.get(i - 1).markDone();
    }
    
    /**
     * Removes the task at index i and returns it.
     *
     * @see #get(int)
     */
    public Task delete(int i) throws IndexOutOfBoundsException {
        return tasks.remove(i - 1);
    }

    /**
     * Exports the tasks in this list into an ArrayList of strings, which can then
     * be written to disk.
     *
     * @return An ArrayList of strings representing this list's tasks.
     */
    public ArrayList<String> export() {
        ArrayList<String> out = new ArrayList<>();
        for (Task t: tasks) {
            out.add(t.export());
        }
        return out;
    }
}
