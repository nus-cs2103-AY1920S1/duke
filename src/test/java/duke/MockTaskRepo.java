package duke;

import duke.repos.Repository;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MockTaskRepo implements Repository<Task> {

    private List<Task> listOfTasks;

    public MockTaskRepo() {
        listOfTasks = new ArrayList<>();
    }

    @Override
    public List<Task> getAll() {
        return listOfTasks;
    }

    @Override
    public void create(Task task) throws IOException {
        listOfTasks.add(task);

    }

    @Override
    public List<Task> find(String query) {
        return listOfTasks.stream()
                .filter(task -> task.name.contains(query))
                .collect(Collectors.toList());
    }

    @Override
    public Task delete(int index) throws IOException {
        return listOfTasks.remove(index - 1);
    }

    @Override
    public void done(int index) throws IOException {
        listOfTasks.get(index - 1).done = true;
    }

    @Override
    public List<Task> sort() {
        return listOfTasks.stream().sorted(Comparator.comparing(t -> t.name))
                .collect(Collectors.toList());
    }
}
