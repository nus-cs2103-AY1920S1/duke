public class Task {
    protected boolean isDone;
    protected String name;

    public Task(String name) {
        this.isDone = false;
        this.name = name;
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