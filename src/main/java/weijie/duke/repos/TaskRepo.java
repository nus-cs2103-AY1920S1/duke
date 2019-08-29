package weijie.duke.repos;

import weijie.duke.db.Storage;
import weijie.duke.exceptions.DukeIOException;
import weijie.duke.models.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskRepo implements IRepository<Task> {

    private Storage storage;
    private List<Task> tasks;

    public TaskRepo(Storage storage) throws DukeIOException {
        this.storage = storage;
        this.tasks = storage.read();
    }

    @Override
    public List<Task> getAll() {
        return tasks;
    }

    @Override
    public int getSize() {
        return tasks.size();
    }

    @Override
    public Task get(int id) {
        return tasks.get(id);
    }

    @Override
    public List<Task> find(String searchParam) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(searchParam))
                .collect(Collectors.toList());
    }

    @Override
    public void create(Task entity) throws DukeIOException {
        tasks.add(entity);
        storage.write(tasks);
    }

    @Override
    public void update(int id, Task entity) throws DukeIOException {
        tasks.set(id, entity);
        storage.write(tasks);
    }

    @Override
    public void delete(int id) {
        tasks.remove(id);
    }
}
