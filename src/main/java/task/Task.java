package task;

import priority.Priority;

public class Task {

    protected String description;
    protected boolean isDone;
    protected Priority priority;

    public Task(String description, int priority) {
        this.description = description;
        this.isDone = false;
        this.priority = whichPriority(priority);
    }

    public Task(String description, int isDone, int priority) {
        this.description = description;
        this.isDone = isDone == 1;
        this.priority = whichPriority(priority);
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return isDone ? "O" : "X";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public Priority whichPriority(int priority) {
        switch (priority) {
        case 1:
            return Priority.HIGH;
        case 2:
            return Priority.MEDIUM;
        case 3:
            return Priority.LOW;
        default:
            return null;
        }
    }

    public Priority getPriority() {
        return this.priority;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}