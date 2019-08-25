package duke.task;

public abstract class Task {
    String description;
    String timing;
    boolean isDone;
    TaskType taskType;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, String timing) {
        this(description);
        this.timing = timing;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTiming() {
        return this.timing;
    }

    //setDone so that if the user accidentally marks something to done, it can be undone
    public void setDone(boolean done) {
        this.isDone = done;
    }

    abstract public String getStatusText();
}
