/*
 * Task.java
 * Level-6
 * CS2103T
 * @author Gabriel Ong
 *
 * This class represents a basic Task.
 *
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
