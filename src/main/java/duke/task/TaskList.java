package duke.task;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of {@link Task}s.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a {@link Task} to the list.
     *
     * @param task the {@link Task} to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the number of {@link Task}s in this list.
     *
     * @return the number of {@link Task}s in this list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the {@link Task} at the specified position in this list.
     *
     * @param id index of the {@link Task} to return
     * @return the {@link Task} at the specified position in this list
     */
    public Task get(int id) {
        return tasks.get(id);
    }

    /**
     * Deletes the element at the specified position in this list.
     *
     * @param id the index of the {@link Task} to be deleted
     * @return the {@link Task} that was removed from the list
     */
    public Task delete(int id) {
        return tasks.remove(id);
    }

    /**
     * Returns a stringified list of every {@link Task} to be used in the {@link duke.storage.Storage}.
     *
     * @return a stringified list of every {@link Task} to be used in the {@link duke.storage.Storage}.
     */
    public String stringify() {
        if (tasks.size() == 0) {
            return "";
        }

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
