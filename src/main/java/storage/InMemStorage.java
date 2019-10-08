package storage;

import duke.task.Task;
import error.storage.StorageException;

import java.util.ArrayList;
import java.util.List;

/**
 * An in-memory storage which is used to represent a temporary Storage with no ability to persist data.
 */
public class InMemStorage implements Storage {
    public List<Task> tasks;

    public InMemStorage() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Method used by the program to read current list of tasks from storage.
     *
     * @return a list of the tasks that have been put in storage.
     * @throws StorageException if something goes wrong while accessing the storage.
     */
    @Override
    public List<Task> getTasks() throws StorageException {
        return this.tasks;
    }

    /**
     * Method used by the program to write new tasks to storage.
     *
     * @param tasks the list of tasks to be written to storage.
     * @throws StorageException if something goes wrong while accessing the storage.
     */
    @Override
    public void writeTasks(List<Task> tasks) throws StorageException {
        this.tasks = tasks;
    }
}
