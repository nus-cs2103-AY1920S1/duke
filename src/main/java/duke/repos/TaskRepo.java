package duke.repos;

import duke.backend.Storage;
import duke.task.Task;
import duke.task.ToDos;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public Task delete(int index) throws IOException {
        Task removed = listOfTasks.remove(index - 1);
        storage.save(listOfTasks);
        return removed;
    }

    @Override
    public List<Task> find(String query) {
        return listOfTasks.stream()
                .filter(task -> task.name.contains(query))
                .collect(Collectors.toList());
    }

    @Override
    public void done(int index) throws IOException {
        listOfTasks.get(index - 1).done = true;
        storage.save(listOfTasks);
    }

    @Override
    public List<Task> sort() {
        return listOfTasks.stream().sorted(Comparator.comparing(t -> t.name))
                .collect(Collectors.toList());
    }
}
