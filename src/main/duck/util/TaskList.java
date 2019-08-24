package duck.util;

import duck.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;
    private static int total;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        total = tasks.size();
    }

    public static int getTotalTask() {
        return total;
    }

    public Task getTaskAt(int index) {
        return tasks.get(index);
    }
    public void add(Task task) {
        total++;
        tasks.add(task);
    }

    public Task removeTaskAt(int index) {
        total--;
        return tasks.remove(index);
    }
}
