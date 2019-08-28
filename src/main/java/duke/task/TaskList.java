package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int id) {
        return tasks.get(id);
    }

    public void delete(int id) {
        tasks.remove(id);
    }

    public String stringify() {
        StringBuilder sb = new StringBuilder(tasks.get(0).stringify());
        for (int i = 1; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            sb.append(System.lineSeparator());
            sb.append(task.stringify());
        }
        return sb.toString();
    }

    /**
     * Returns a new {@link TaskList} with {@link Task}s that contains the keyword.
     *
     * @param keyword the string to search for.
     * @return a new {@link TaskList} with the filtered copy.
     */
    public TaskList find(String keyword) {
        return new TaskList(tasks.stream()
                .filter(task -> task.description.contains(keyword))
                .collect(Collectors.toList()));
    }
}
