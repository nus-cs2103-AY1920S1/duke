package duke.task;

import java.util.function.Function;

/**
 * Parent class of all tasks. Tasks are identified by having a description and its completion
 * status.
 */
public abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;
    protected int priority;

    /**
     * Abstract constructor that cannot be used to instantiate a {@link Task},
     * can be inherited to initialise properties in {@link Task}.
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = -1;
    }

    /**
     * Returns a string formatted to style suitable for {@link duke.util.Storage}.
     *
     * @return String formatted for {@link duke.util.Storage}.
     */
    public abstract String getStorageSyntax();

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Returns the description of the {@link Task}.
     *
     * @return a string describing the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the {@link Task} to the given input.
     *
     * @param description User input of new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns completion status of the {@link Task}.
     *
     * @return Returns true if task is completed.
     */
    public boolean checkDone() {
        return isDone;
    }

    /**
     * Sets the completion status of the {@link Task}.
     *
     * @param done User input of new completion state.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns icon depicting completions status for the {@link Task}.
     *
     * @return return tick or X symbols
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    /**
     * Returns true if {@link Task} description contains user's key string.
     *
     * @param keyString Key string user intends to find.
     * @return Returns true if {@link Task} description contains user's key string.
     */
    public boolean containsKeyString(String keyString) {
        return this.getDescription().contains(keyString);
    }

    /**
     * Returns string representation of properties in the {@link Task}.
     *
     * @return string representation of task properties.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    @Override
    public int compareTo(Task o) {
        return this.getPriority();
    }
}
