package duke.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public boolean addTask(final Task task) {
        return tasks.add(task);
    }

    public Task getTask(final int index) {
        return tasks.get(index);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index index of the task to be deleted
     * @return the deleted task
     */
    public Task deleteTask(final int index) {
        Task task = getTask(index);
        tasks.remove(index);
        return task;
    }

    public int size() {
        return tasks.size();
    }
}
