package utils;

import tasks.Task;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int taskId) {
        return this.tasks.get(taskId);
    }

    /**
     * Adds tasks to the list
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Adds tasks to the list
     * @param taskId The task to delete
     * @return Task that was deleted
     */
    public Task deleteTask(int taskId) {
        return this.tasks.remove(taskId);
    }
}
