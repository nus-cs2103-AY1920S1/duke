package duke.task;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public static Task get(int index) {
        return tasks.get(index);
    }

    public static int size() {
        return tasks.size();
    }

    public static void remove(int index) {
        tasks.remove(index);
    }

    public static void add(Task task) {
        tasks.add(task);
    }

}
