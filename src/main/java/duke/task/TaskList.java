package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that holds a list of tasks that may be added to, removed or
 * marked as done. This list is indexed starting from 1.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final Pattern TASK_PAT = Pattern.compile("([TED])\\|([01])\\|(.*?)\\|(.*?)");
    
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
            Matcher m = TASK_PAT.matcher(stringTask);
            if (!m.matches()) {
                System.out.println("Ignoring malformed task line " + stringTask);
                continue;
            }

            Task t = null;
            switch (m.group(1)) {
            case "T":
                t = new Todo(m.group(3));
                break;
            case "E":
                t = new Event(m.group(3), LocalDateTime.parse(m.group(4), INPUT_FORMATTER));
                break;
            case "D":
                t = new Deadline(m.group(3), LocalDateTime.parse(m.group(4), INPUT_FORMATTER));
                break;
            default: // Can't reach here because the regex would have rejected it
                break;
            }

            assert t != null;
            if (m.group(2).equals("1")) {
                t.markDone();
            }
            tasks.add(t);
        }
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @return The size of this instance's list, which is zero if and only if
     *     said list is empty.
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

    /**
     * Filters this instance's list for those tasks matching a given keyword.
     *
     * @param keyword The keyword being searched for.
     * @return A smaller or same-size ArrayList containing those tasks with the given keyword.
     */
    public ArrayList<Task> filter(String keyword) {
        ArrayList<Task> out = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDesc().contains(keyword)) {
                out.add(task);
            }
        }
        return out;
    }
}
