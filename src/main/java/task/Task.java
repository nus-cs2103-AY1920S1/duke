package seedu.duke.task;

public abstract class Task {

    String taskName;
    boolean isDone;

    // Default constructor for this class
    public Task(String task) {
        this.taskName = task;
        this.isDone = false;
    }

    // Returns a string containing the information of this object.
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.taskName);
    }

    // Marks this task as done
    public void markAsDone() {
        this.isDone = true;
    }

    // Method used to convert this object into a storable string form.
    public abstract String toStorageString();

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
}
