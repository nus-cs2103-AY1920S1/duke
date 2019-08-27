public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public TaskType getType() {
        return type;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }

    public boolean doTask() {
        if (!isDone) {
            this.isDone = true;
            return false;
        } else {
            return true;
        }
    }
}