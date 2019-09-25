package weijie.duke.repos;

import weijie.duke.db.ITaskStorage;
import weijie.duke.exceptions.DukeIoException;
import weijie.duke.models.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskRepo implements IRepository<Task> {

    private ITaskStorage storage;
    private List<Task> tasks;


    /**
     * Constructs a new {@code Task} repository that uses the given {@code ITaskStorage} to store and retrieve the
     * tasks.
     *
     * @param storage Storage to store and retrieve the tasks from.
     * @throws DukeIoException If reading or writing to/from the storage throws an IO Exception.
     */
    public TaskRepo(ITaskStorage storage) throws DukeIoException {
        assert storage != null;
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
    public void create(Task entity) throws DukeIoException {
        assert entity != null;
        tasks.add(entity);
        storage.write(tasks);
    }

    @Override
    public void insert(int id, Task entity) throws DukeIoException {
        assert entity != null;
        tasks.add(id, entity);
        storage.write(tasks);
    }

    @Override
    public void update(int id, Task entity) throws DukeIoException {
        assert entity != null;
        tasks.set(id, entity);
        storage.write(tasks);
    }

    @Override
    public void delete(int id) throws DukeIoException {
        tasks.remove(id);
        storage.write(tasks);
    }

    @Override
    public void delete(Task task) throws DukeIoException {
        tasks.remove(task);
        storage.write(tasks);
    }
}
