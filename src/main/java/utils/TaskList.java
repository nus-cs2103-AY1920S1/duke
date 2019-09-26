package utils;

import exceptions.DukeException;
import tasks.Task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private List<Task> snapshot;

    /**
     * Enable access to the current lists of tasks.
     *
     * @param tasks list of tasks created from stored data
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        cloneTaskList();
    }

    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Retrieve task from the task list.
     *
     * @param taskId of the task
     * @return task required
     */
    public Task getTask(int taskId) {
        return this.tasks.get(taskId);
    }

    /**
     * Creates a snapshot of the current task list.
     * Adds tasks to the list.
     *
     * @param task to add to list
     */
    public void addTask(Task task) {
        cloneTaskList();
        this.tasks.add(task);
    }

    /**
     * Creates a snapshot of the current task list.
     * Delete tasks from the list.
     *
     * @param taskId the task to delete
     * @return task that was deleted
     */
    public Task deleteTask(int taskId) {
        cloneTaskList();
        return this.tasks.remove(taskId);
    }

    /**
     * Creates a snapshot of the current task list.
     * Mark the task completed as done.
     *
     * @param taskId the task that was completed
     */
    public void markTaskAsDone(int taskId) throws DukeException {
        cloneTaskList();
        Task doneTask = tasks.remove(taskId - 1);
        Task clone = doneTask.markAsDone();
        tasks.add(taskId - 1, clone);
    }

    /**
     * Hold a copy of the most recent task list.
     *
     */
    private void cloneTaskList() {
        this.snapshot = new ArrayList<>(this.tasks);
    }

    /**
     * Restores the list to the most recent version.
     * Used by the undo command.
     *
     */
    public void restoreToSnapshot() {
        this.tasks = new ArrayList<>(this.snapshot);
    }

    /**
     * Finds the matching tasks from the current list of tasks,
     * which match the keywords given keyword.
     *
     * @param keyword to match
     * @return list of tasks that match with keyword
     */
    public List<Task> findMatchingTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();

        for (Task task: tasks) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}
