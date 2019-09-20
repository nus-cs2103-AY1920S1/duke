package duke;

import duke.task.Task;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> tasks;

    /**
     * Constructs a task list.
     */
    TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add a task to the list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param taskId Task ID.
     * @return Task that was deleted.
     * @throws IndexOutOfBoundsException If ID is not found.
     */
    public Task deleteTask(int taskId) throws IndexOutOfBoundsException {
        return this.tasks.remove(taskId - 1);
    }

    /**
     * Marks a task as done.
     *
     * @param taskId Task ID.
     * @return Task that was marked done.
     * @throws IndexOutOfBoundsException If ID is not found.
     */
    public Task markDone(int taskId) throws IndexOutOfBoundsException {
        Task task = this.tasks.get(taskId - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Generates a numbered list of tasks matching a query.
     *
     * @param query Query string.
     * @return Numbered list of matching tasks.
     */
    public TaskList find(String query) {
        TaskList matches = new TaskList();

        for (Task t : this.tasks) {
            if (t.getDescription().contains(query)) {
                matches.addTask(t);
            }
        }

        return matches;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Replaces current task list with tasks from another storage instance.
     *
     * @param storage Target storage instance.
     */
    public void importFrom(Storage storage) {
        this.tasks = storage.load().tasks;
    }

    /**
     * Generates numbered list of all tasks in the list.
     *
     * @return Numbered list of all tasks.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            output.append(String.format("%d. %s%n", i + 1, tasks.get(i).toString()));
        }
        return output.toString().trim();
    }
}
