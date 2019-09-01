package task;

public class Task {
    private boolean isDone;
    private String name;

    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }
    public Task(String name, boolean isDone) {
        this.isDone = isDone;
        this.name = name;
    }

    public boolean checkIsDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✗"); //return tick or X symbols
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.getName();
    }

}