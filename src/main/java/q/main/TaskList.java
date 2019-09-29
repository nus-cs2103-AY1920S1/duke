package q.main;

import q.error.NoSuchTaskError;
import q.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of Tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList from a provided list of Tasks.
     *
     * @param tasks List of Tasks that the TaskList should hold.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return If the TaskList is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Gets the size of the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets a List of Strings representing the Tasks in a readable UI format.
     *
     * @return A List of Strings representing the Tasks in a readable UI format.
     */
    ArrayList<String> toUiStringList() {
        int i = 1;
        ArrayList<String> result = new ArrayList<>();
        for (Task t : tasks) {
            StringBuilder sb = new StringBuilder();
            sb.append(i++);
            sb.append(".");
            sb.append(t);
            result.add(sb.toString());
        }
        return result;
    }

    /**
     * Gets a List of Strings representing the Tasks in a format for saving.
     *
     * @return A List of Strings representing the Tasks in a format for saving.
     */
    ArrayList<String> toSaveStringList() {
        ArrayList<String> strings = new ArrayList<>();
        for (Task t : tasks) {
            strings.add(t.toSave());
        }
        return strings;
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber The index of the task.
     * @return The task that was marked.
     * @throws NoSuchTaskError When the task does not exist.
     */
    public Task doTask(int taskNumber) throws NoSuchTaskError {
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new NoSuchTaskError();
        }
        Task doneTask = tasks.get(taskNumber);
        doneTask.setDone();
        assert (doneTask.isDone());
        return doneTask;
    }

    /**
     * Deletes a task.
     *
     * @param taskNumber The index of the task.
     * @return The task that was deleted
     * @throws NoSuchTaskError When the task does not exist.
     */
    public Task deleteTask(int taskNumber) throws NoSuchTaskError {
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new NoSuchTaskError();
        }
        Task deleted = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        return deleted;
    }

    /**
     * Adds a new Task.
     *
     * @param task The task to be added.
     * @return The task that was added.
     */
    public Task addTask(Task task) {
        tasks.add(task);
        assert (tasks.size() > 0);
        return task;
    }

    /**
     * Finds a task containing the search term.
     *
     * @param searchTerm The search term.
     * @return A TaskList with the relevant found tasks.
     */
    public TaskList find(String searchTerm) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().contains(searchTerm)) {
                foundTasks.add(t);
            }
        }
        return new TaskList(foundTasks);
    }

    /**
     * Empties the TaskList.
     */
    public void clear() {
        tasks.clear();
    }
}
