package duke.storage;

import duke.task.TaskList;

public class StorageStub implements Storage {
    protected TaskList tasks;

    public StorageStub(TaskList tasks) {
        store(tasks);
    }

    public void store(TaskList tasks) {
        this.tasks = new TaskList(tasks);
    }

    public TaskList load() {
        return this.tasks;
    }
}
