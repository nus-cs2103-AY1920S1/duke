package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

public class Tasklist {
    public static ArrayList<Task> tasks;

    public Tasklist() {
        tasks = new ArrayList<>(100); // Specification said numTasks < 100.
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }
}
