package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of {@link Task}s.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

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
        StringBuilder sb = new StringBuilder(tasks.get(0).stringify());
        for (int i = 1; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            sb.append(System.lineSeparator());
            sb.append(task.stringify());
        }
        return sb.toString();
    }
}
