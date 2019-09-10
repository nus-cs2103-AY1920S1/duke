package task;

import priority.Priority;

public class Task {

    protected String description;
    protected boolean isDone;
    protected Priority priority;

    /**
     * Constructor for Task. More commonly used.
     * 
     * @param description Description of Task.
     * @param priority    Priority level of the task.
     */
    public Task(String description, int priority) {
        this.description = description;
        this.isDone = false;
        this.priority = whichPriority(priority);
    }

    /**
     * Another constructor for Task. Used mainly in Storage.
     * 
     * @param description Description of Task.
     * @param isDone      1 if the Task is done, else 0.
     * @param priority    Priority level of the task.
     */
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

    /**
     * Converts priority from Integer to Priority.
     * 
     * @param priority Priority in Integer object.
     * @return Priority with correct priority level.
     */
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