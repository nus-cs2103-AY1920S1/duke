public abstract class Task {
    protected boolean isCompleted;
    protected String taskName;

    public Task(String taskName) {
        this.isCompleted = false;
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String toString() {
        String statusIcon = this.isCompleted ? "✓" : "✗";
        return String.format("[%s] %s", statusIcon, this.taskName);
    }
}
