package duke.task;

import duke.Storage;

import java.io.IOException;
import java.util.List;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    static List<String> addTask(Task task, TaskList tasks, Storage storage) throws IOException {
        tasks.add(task);
        storage.store(tasks.getAsLines());
        return List.of("Got it. I've added this task:", "  " + task,
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the status icon of this task for display to the user.
     *
     * @return Status icon.
     */
    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns a list of strings representing this task so that it can be saved.
     *
     * @return A representation of this task as a list for saving.
     */
    public List<String> getSaveList() {
        return List.of(getTaskType(), isDone ? "1" : "0", description);
    }

    /**
     * Returns the type of this task.
     *
     * @return Type of this task as a string.
     */
    abstract String getTaskType();

    /**
     * Returns this task as a string to display to the user.
     *
     * @return This task as a string.
     */
    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + description;
    }
}
