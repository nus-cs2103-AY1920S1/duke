package jermi.component;

import jermi.task.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public List<Task> getList() {
        return this.list;
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public int getSize() {
        return this.list.size();
    }

    public Task getTask(int ordering) {
        return this.list.get(ordering - 1);
    }

    public void remove(int ordering) {
        this.list.remove(ordering - 1);
    }

    public List<String> find(String keyword) {
        return this.list
                .stream()
                .map(Task::toString)
                .filter(task -> task.contains(keyword))
                .collect(Collectors.toList());
    }
}
