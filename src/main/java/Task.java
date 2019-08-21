public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String type, String description) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + this.type + "][" + getStatusIcon() + "] " + this.description;
    }
}