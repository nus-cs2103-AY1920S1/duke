package weijie.duke.repos;

import weijie.duke.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepo implements IRepository<Task> {

    private List<Task> tasks;

    public TaskRepo() {
        tasks = new ArrayList<>();
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
    public void create(Task entity) {
        tasks.add(entity);
    }

    @Override
    public void update(int id, Task entity) {
        tasks.set(id, entity);
    }
}
