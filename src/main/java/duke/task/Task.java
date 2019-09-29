package duke.task;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private String description;
    private Boolean isDone;
    String type;

    public Task(String description) {
        // todo: add new constructor to create new task from String
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        String type = "[" + this.type + "] ";
        String status = "[" + this.getStatusIcon() + "] ";
        return type + status + this.description;
    }

    String getDescription() {
        return this.description;
    }

    private String getStatusIcon() {
        return (isDone ? "Y" : "N"); // return Y or N
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
    }

    public enum TaskType {
        DEADLINE,
        EVENT,
        TODO
    }

    // todo: use TaskType enum to get type in toString() instead
}
