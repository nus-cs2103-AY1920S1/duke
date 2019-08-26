package bin.task;

public class Task {
    protected boolean isDone;
    protected String name;

    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public Task completed() {
        isDone = true;
        return this;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }
}