package duke.task;

public abstract class Task {
    private String description;
    private boolean isDone;
    public static int totalTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, int done) {
        this.description = description;
        this.isDone = false;
        if (done == 1) {
            this.isDone = true;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setTaskCompleted() {
        isDone = true;
    }

    public boolean getDoneStatus() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }

}
