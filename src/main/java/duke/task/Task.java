package duke.task;

public abstract class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return isDone;
    }

    /**
     * Set the status icon to tick.
     */
    public void completeTask() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return isDone ? "✓" : "✗";
    }

    public abstract char getShortForm();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getName();
    }
}