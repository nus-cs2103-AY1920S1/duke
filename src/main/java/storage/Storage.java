package storage;

import duke.task.Task;
import error.storage.StorageException;

import java.util.List;

/**
 * An interface to encapsulate a class that handles reading and writing of tasks to storage.
 */
public interface Storage {
    /**
     * Method used by the program to read current list of tasks from storage.
     * @return a list of the tasks that have been put in storage.
     * @throws StorageException if something goes wrong while accessing the storage.
     */
    public List<Task> getTasks() throws StorageException;

    /**
     * Method used by the program to write new tasks to storage.
     * @param tasks the list of tasks to be written to storage.
     * @throws StorageException if something goes wrong while accessing the storage.
     */
    public void writeTasks(List<Task> tasks) throws StorageException;
}
