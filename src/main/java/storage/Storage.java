package storage;

import duke.task.Task;
import error.storage.StorageException;

import java.util.List;

public interface Storage {
    public List<Task> getTasks() throws StorageException;
    public void writeTasks(List<Task> tasks) throws StorageException;
}
