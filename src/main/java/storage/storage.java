package storage;

import task.tasks.Task;

import java.util.List;

public interface storage {
    public List<Task> getTasks();
    public void writeTasks(List<Task> tasks);
}
