package duck.util;

import duck.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getTotalTask() {
        return tasks.size();
    }

    public Task getTaskAt(int index) {
        return tasks.get(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void addAll(TaskList another) {
        for (int i = 0; i < another.getTotalTask(); i++) {
            tasks.add(another.getTaskAt(i));
        }
    }

    public Task removeTaskAt(int index) {
        return tasks.remove(index);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TaskList) {
            TaskList another = (TaskList) obj;
            if (another.getTotalTask() != this.getTotalTask()) {
                return false;
            } else {
                for (int i = 0; i < this.getTotalTask(); i++) {
                    if (!this.getTaskAt(i).equals(another.getTaskAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }
}
