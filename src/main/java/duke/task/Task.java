package duke.task;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private String description;
    private Boolean isDone = false;
    TaskType type;

    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String type = "[" + this.type.getTaskTypeInitial() + "] ";
        String status = "[" + this.getStatusIcon() + "] ";
        return type + status + this.description;
    }

    private String getStatusIcon() {
        return (isDone ? "Y" : "N"); // return Y or N
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
    }
}
