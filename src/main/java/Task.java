public class Task {
    protected String description;
    protected boolean isDone;
    protected int status;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.status = 0;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    //...
    public void markAsDone() {
        this.isDone = true;
        this.status = 1;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public int getStatus() {
        return this.status;
    }

    public String getDescription() {
        return description;
    }
}