package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int id) {
        return tasks.get(id);
    }

    public Task delete(int id) {
        return tasks.remove(id);
    }

    public String stringify() {
        StringBuilder sb = new StringBuilder(tasks.get(0).stringify());
        for (int i = 1; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            sb.append(System.lineSeparator());
            sb.append(task.stringify());
        }
        return sb.toString();
    }
}
