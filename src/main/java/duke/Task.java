package duke;
/*
 * Task.java
 * CS2103T
 * @author Gabriel Ong
 *
 * This class represents a basic Task.
 *
 */

import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    private boolean isDone;

    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    protected String getStatusIcon() {
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
