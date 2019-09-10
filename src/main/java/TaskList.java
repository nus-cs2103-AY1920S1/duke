import java.util.ArrayList;

/** Handles events associated with the list of tasks. */
public class TaskList {
    private static ArrayList<Task> storage;

    /** Creates empty task list. */
    public TaskList() {
        storage = new ArrayList<Task>();
    }

    /** Creates list with existing list of tasks. */
    public TaskList(ArrayList<Task> tasks) {
        storage = tasks;
    }

    /**
     * Gets the 1-indexed i-th task.
     *
     * @param i The i-th task.
     * @return The task.
     */
    public static Task get(int i) throws DukeException {
        try {
            return storage.get(i - 1);
        } catch (Exception e) {
            throw new DukeException("That task doesn't exist!");
        }
    }

    /** Prints nice text about how many tasks are in the list. */
    private static String getStorageSize() {
        return "Now you have " + storage.size() + " tasks in your list.";
    }

    public static String stringifyTasks(ArrayList<Task> a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= a.size(); i++) {
            Task t = a.get(i - 1);
            sb.append(String.format("%d. %s\n", i, t.toString()));
        }
        return sb.toString();
    }

    /** Prints out all the tasks in the list. */
    public static String stringifyTasks() {
        return stringifyTasks(storage);
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to be added.
     */
    public static String addTask(Task t) {
        storage.add(t);
        Storage.saveTasks(storage);
        return "Got it. I've added this task:\n" + t.toString() + "\n" + getStorageSize();
    }

    /**
     * Removes the 1-indexed i-th task from the list.
     *
     * @param the i-th task to remove.
     */
    public static String removeTask(int i) throws DukeException {
        Task t;
        try {
            t = storage.get(i);
            storage.remove(i);
        } catch (Exception e) {
            throw new DukeException("That task doesn't exist!");
        }
        Storage.saveTasks(storage);
        return String.format("Noted. I've removed this task:\n  %s", t.toString())
                + getStorageSize();
    }

    /**
     * Searches the list of tasks for something and returns everything that matches.
     *
     * @param s The string to search for.
     * @return An arraylist of tasks that contains all matches.
     */
    public static ArrayList<Task> query(String s) {
        ArrayList<Task> res = new ArrayList<Task>();
        for (Task t : storage) {
            if (t.getDesc().contains(s)) {
                res.add(t);
            }
        }
        return res;
    }
}
