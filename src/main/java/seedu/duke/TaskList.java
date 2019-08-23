package seedu.duke;

import seedu.duke.task.Task;

import java.util.ArrayList;

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

    public void notifyChange() {
        storage.save(this);
    }
}
