package duke.task;

/**
 * Represents a task to be completed. A task has a description and a boolean
 * state isDone, describing if the task is completed or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setStatus(String status) {
        this.isDone = (status.equals("1")) ? true : false;
    }

    public String toString() {
        return description;
    }

    public void setIsDone() {
        this.isDone = true;
    }

    public char getTaskType() {
        return 'n';
    }

    public String getDate() {
        return null;
    }

    public String getDescription() {
        return description;
    }
}