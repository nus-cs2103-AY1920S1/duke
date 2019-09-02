package duke.task;

/**
 * Represents a task given to the bot by the user.
 */
public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String statusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("[%s] | %s", this.statusIcon(), this.name);
    }
}
