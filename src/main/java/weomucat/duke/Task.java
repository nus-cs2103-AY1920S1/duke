package weomucat.duke;

public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
