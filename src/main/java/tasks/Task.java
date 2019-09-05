package tasks;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDone() {
        this.isDone = true;
    }

    public String toSave() {
        return (isDone ? "1" : "0") + " | " + this.description;
    }

}
