package storage;

import duke.task.tasks.Task;
import error.StorageException;

import java.util.List;

public interface Storage {
    public List<Task> getTasks() throws StorageException;
    public void writeTasks(List<Task> tasks) throws StorageException;
}
