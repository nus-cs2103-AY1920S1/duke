public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "+" : " ");
    }

    public String getType() {
        return "task";
    }

    public String getTypeIcon() {
        return "[]";
    }

    public String getDate() {
        return "";
    }

    @Override
    public String toString() {
        return taskName;
    }
}
