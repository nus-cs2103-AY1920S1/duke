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
        return (isDone ? "X" : "O"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "added: " + description;
    }
}