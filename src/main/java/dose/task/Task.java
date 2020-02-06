package dose.task;

import java.io.Serializable;

/**
 * Represents a task, the building block of a TaskList object.
 */
public abstract class Task implements Serializable {
    private String description;
    private Boolean isDone = false;
    TaskType type;
    private String tag;
    private TaskPriority priority;

    /**
     * Constructs a Task, with the specified description.
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns the String representation of a Task for display purposes.
     * @return String representation of a Task for display purposes.
     */
    @Override
    public String toString() {
        String typeInitial = "[" + this.type.getTaskTypeInitial() + "] ";
        String status = "[" + this.getStatusIcon() + "] ";

        assert this.description != null;
        String toString = typeInitial + status + this.description;

        if (this.hasTag()) {
            toString = toString + " (tag: " + this.getTag() + ")";
        }

        if (this.hasPriority()) {
            toString = toString + " (priority: " + this.getPriority() + ")";
        }
        return toString;
    }

    /**
     * Returns the status icon corresponding to the status of the task (done or not done).
     * @return Status icon corresponding to the status of the task (done or not done).
     */
    private String getStatusIcon() {
        return (isDone ? "Y" : "N"); // return Y or N
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Postpones the deadline of the given task by 1 day if no other argument is specified.
     */
    public abstract void snooze();

    /**
     * Adds a tag to the given task.
     * Currently supports only one tag per task.
     * To override existing tag, use tag command.
     * @param tag Tag to be added to the given task.
     */
    public void addTag(String tag) {
        this.tag = tag;
    }

    private String getTag() {
        return this.tag;
    }

    private boolean hasTag() {
        return this.getTag() != null;
    }

    /**
     * Adds a priority to the given task.
     * @param priority Priority to be added to the given task.
     */
    public void addPriority(TaskPriority priority) {
        this.priority = priority;
    }

    private TaskPriority getPriority() {
        return this.priority;
    }

    private boolean hasPriority() {
        return this.getPriority() != null;
    }
}
