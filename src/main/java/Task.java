public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A class that handle the creation, deletion, and markAsDone of a task.
     * This class has 3 subclass: (Deadline, Event, and Todo class).
     *
     * @param description the description of a task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "added: " + description;
    }
}