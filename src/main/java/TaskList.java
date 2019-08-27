package duke;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> storage;

    public TaskList() {
        storage = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        storage = tasks;
    }

    public static Task get(int i) {
        return storage.get(i - 1);
    }

    private static void printStorageSize() {
        System.out.println(
            "Now you have " + storage.size() + " tasks in your list."
        );
    }

    public static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= storage.size(); i++) {
            Task t = storage.get(i - 1);
            System.out.println(String.format("%d. %s", i, t.toString()));
        }
    }

    public static void addTask(Task t) {
        System.out.println("Got it. I've added this task:\n" + t.toString());
        storage.add(t);
        Storage.saveTasks(storage);
        printStorageSize();
    }

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
