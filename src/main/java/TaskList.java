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

    public static void printTasks(ArrayList<Task> a) {
        for (int i = 1; i <= a.size(); i++) {
            Task t = a.get(i - 1);
            System.out.println(String.format("%d. %s", i, t.toString()));
        }
    }

    public static void printTasks() {
        printTasks(storage);
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
