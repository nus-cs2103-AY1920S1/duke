package seedu.duke.tasks;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private static final long serialVersionUID = -2528903467902347L;

    private final String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", done ? "\u2713" : " ", description);
    }
}
