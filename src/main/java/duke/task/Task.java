package duke.task;

public abstract class Task {

    private String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public abstract String getType();

    public String getStatus() {
        String icon = (isDone ? "\u2713" : "\u2718"); //tick or X symbol
        return String.format("[%s] %s", icon, this.description);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.description;
    }

}