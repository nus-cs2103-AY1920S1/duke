package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected int num;

    public Task() {
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.num = 0;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setStatusIcon(boolean b) {
        isDone = b;
        if (isDone) {
            num = 1;
        }
    }

    public String getDesc() {
        return description;
    }

    public String format() {
        return "|" + num + "|" + description;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
