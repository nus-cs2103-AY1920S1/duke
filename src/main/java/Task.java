/**
 * A base class that Deadline, Event, and ToDo inherit from.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String typeOfTask = "";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Marks the current Task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String statusIcon = isDone ? "[\u2713]" : "[\u2718]"; // \u2713 represents a tick, \u2718 represents a cross
        return String.format("%s%s %s", typeOfTask, statusIcon, description);
    }

    public String writeToFile() {
        String status = isDone ? "1" : "0";
        return String.format("%s|%s|%s", typeOfTask, status, description);
    }
}
