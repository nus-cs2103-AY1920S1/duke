//Solution below adapted from https://nus-cs2103-ay1920s1.github.io/website/schedule/week2/project.html

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✗"); //return tick or X symbols
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}