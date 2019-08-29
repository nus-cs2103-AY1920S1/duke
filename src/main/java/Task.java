public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String type = "";
    protected String date = "";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getType() {
        return this.type;
    }

    public String getDate() {
        return this.date;
    };

    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    public String getStatusNum() {
        return isDone ? "1" : "0";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
