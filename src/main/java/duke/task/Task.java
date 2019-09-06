package duke.task;

/**
 * Represents a task object.
 */
public class Task {
    // Instance variables
    public String description;
    public boolean isDone;

    // Constructors
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    // Getters & Setters
    public String getDescription() {
        return description;
    }

    /**
     * Sets the task to done or undone.
     * @param done Boolean indicating whether the task is done or not.
     * @return the task itself.
     */
    public Task setDone(boolean done) {
        isDone = done;
        return this;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Represents done or undone. Provided in the CS2103T task description.
     * @return a graphic representation of 1/0.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns a string representation of the object.
     * @return  a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }

    /**
     * Returns the description of the task.
     * @return task description.
     */
    public String toStorage() {
        return description;
    }
}