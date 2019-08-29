package weijie.duke.commands;


import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class MockTaskRepo implements IRepository<Task> {

    private List<Task> tasks;

    MockTaskRepo() {
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
    public List<Task> find(String searchParam) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(searchParam))
                .collect(Collectors.toList());
    }

    @Override
    public void create(Task entity) {
        tasks.add(entity);
    }

    @Override
    public void update(int id, Task entity) {
        tasks.set(id, entity);
    }

    @Override
    public void delete(int id) {
        tasks.remove(id);
    }
}
