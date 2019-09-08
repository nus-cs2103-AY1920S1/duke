package storage;

import duke.task.tasks.Task;

import java.util.List;

public interface Storage {
    public List<Task> getTasks();
    public void writeTasks(List<Task> tasks);
}
