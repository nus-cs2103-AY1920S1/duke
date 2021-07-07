public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    //✓u2713
    public String getStatusIcon() {
        return (isDone ? "DONE" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
