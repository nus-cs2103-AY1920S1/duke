package duke.repos;

import duke.backend.Storage;
import duke.task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class TaskRepo implements Repository<Task> {
    private Storage storage;
    private List<Task> listOfTasks;

    public TaskRepo(Storage storage) throws IOException {
        this.storage = storage;
        this.listOfTasks = storage.load();
    }

    @Override
    public List<Task> getAll() {
        return listOfTasks;
    }

    @Override
    public void create(Task task) throws IOException {
        listOfTasks.add(task);
        storage.save(listOfTasks);
    }

    @Override
    public void insert(int index, Task entity) {

    }

    @Override
    public void delete(int index) {

    }
}
