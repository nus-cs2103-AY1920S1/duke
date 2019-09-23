package duke.storage;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * The Storage interface for the Duke Program.
 */
public interface Storage {
    /**
     * Stores a list of tasks into the storage.
     *
     * @param tasks The list of tasks to be stored.
     * @throws DukeException If the process of storing the list of tasks fails.
     */
    void store(TaskList tasks) throws DukeException;

    /**
     * Loads and returns a list of tasks from the storage.
     *
     * @return The list of task of tasks from the storage
     * @throws DukeException If the process of loading the list of tasks fails.
     */
    TaskList load() throws DukeException;
}