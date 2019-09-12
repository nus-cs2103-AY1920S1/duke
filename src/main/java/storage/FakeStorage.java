package storage;

import task.Task;
import task.TaskList;

import java.util.ArrayList;

public class FakeStorage extends Storage {

    /**
     * FakeStorage constructor when file is not found.
     */
    public FakeStorage() {
        super();
    }

    /**
     * Load method meant to just overload.
     * @return an empty Tasks array list.
     */
    public ArrayList<Task> load() {
        return new ArrayList<Task>();
    }

    /**
     * Save method meant to just overload.
     * Actually does nothing.
     * @param tasks the tasks list to store.
     */
    public void save(TaskList tasks) {
    }

}
