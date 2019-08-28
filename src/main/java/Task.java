public class Task {
    protected String description;
    protected boolean isDone;

    public Task(boolean done, String description) {
        this.description = description;
        this.isDone = done;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    public String getDesc() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDesc());
    }

    public String saveFormat() {
        return String.format("| %d | %s\n", isDone ? 1 : 0, getDesc());
    }
}
