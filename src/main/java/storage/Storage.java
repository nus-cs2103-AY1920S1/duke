package storage;

import duke.task.Task;
import error.storage.StorageException;

import java.util.List;

/**
 * Interface to handle reading and writing of tasks to a storage.
 */
public interface Storage {
    public List<Task> getTasks() throws StorageException;

    public void writeTasks(List<Task> tasks) throws StorageException;
}
