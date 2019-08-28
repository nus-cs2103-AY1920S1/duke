package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public boolean addTask(final Task task) {
        return tasks.add(task);
    }

    public Task getTask(final int index) {
        return tasks.get(index);
    }

    public Task deleteTask(final int index) {
        Task task = getTask(index);
        tasks.remove(index);
        return task;
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            ret.append(i + 1)
                .append(".")
                .append(tasks.get(i).toString())
                .append("\n");
        }
        return ret.toString();
    }

    public String toStorageString() {
        StringBuilder ret = new StringBuilder();
        for (Task task : tasks) {
            ret.append(task.toStorageString())
                .append("\n");
        }
        return ret.toString();
    }
}
