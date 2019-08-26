public class Task {
    protected String description;
    protected boolean isDone;
    protected String extraInfo;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.extraInfo = "";
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getExtraInfo() {
        return this.extraInfo;
    }

    public String getStatusIcon() {
        return (isDone ? "V" : "X");
    }

    public void completeTask() {
        this.isDone = true;
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
