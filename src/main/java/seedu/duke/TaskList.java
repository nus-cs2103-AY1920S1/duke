package seedu.duke;

import seedu.duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks. Every time the data is changed, the new data will be saved.
 */
public class TaskList extends ArrayList<Task> {
    private Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
    }

    @Override
    public boolean add(Task task) {
        boolean added = super.add(task);
        storage.save(this);
        return added;
    }

    @Override
    public Task remove(int index) {
        Task removed = super.remove(index - 1);
        storage.save(this);
        return removed;
    }

    @Override
    public Task get(int index) {
        return super.get(index - 1);
    }

    /**
     * Notifies the change of the tasks such that the tasks can be saved.
     */
    public void notifyChange() {
        storage.save(this);
    }
}
