package duke;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> storage;

    /**
     * Creates empty task list.
     */
    public TaskList() {
        storage = new ArrayList<Task>();
    }

    /**
     * Creates list with existing list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        storage = tasks;
    }

    /**
     * Gets the 1-indexed i-th task.
     * @param i The i-th task.
     * @return The task.
     */
    public static Task get(int i) {
        return storage.get(i - 1);
    }

    /**
     * Prints nice text about how many tasks are in the list.
     */
    private static void printStorageSize() {
        System.out.println(
            "Now you have " + storage.size() + " tasks in your list."
        );
    }

    /**
     * Prints out all the tasks in the list.
     */
    public static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= storage.size(); i++) {
            Task t = storage.get(i - 1);
            System.out.println(String.format("%d. %s", i, t.toString()));
        }
    }

    /**
     * Adds a task to the list.
     * @param t The task to be added.
     */
    public static void addTask(Task t) {
        System.out.println("Got it. I've added this task:\n" + t.toString());
        storage.add(t);
        Storage.saveTasks(storage);
        printStorageSize();
    }

    /**
     * Removes the 1-indexed i-th task from the list.
     * @param the i-th task to remove.
     */
    public static void removeTask(int i) {
        Task t = storage.get(i);
        System.out.println(
            String.format("Noted. I've removed this task:\n  %s", t.toString())
        );
        storage.remove(i);

        // Storage.saveTasks(storage);
        // needed?
        printStorageSize();
    }
}
