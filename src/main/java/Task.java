import java.util.LinkedList;

public class Task {
    protected String description;
    protected boolean isDone;

    // the parent class of Deadline, Event and To-do classes
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
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