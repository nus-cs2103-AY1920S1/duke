package duke.task;

/**
 *
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

    public boolean isDone() {
        return isDone;
    }

    public Task setDone(boolean done) {
        isDone = done;
        return this;
    }

    public void markAsDone() {
        isDone = true;
    }

    /**
     * dot. Provided in the CS2103T task description.
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

    public String toStorage() {
        return description;
    }
}