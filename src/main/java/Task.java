public class Task {
    private boolean isCompleted;
    private String taskName;

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

    @Override
    public String toString() {
        String statusIcon = this.isCompleted ? "✓" : "✗";
        return String.format("[%s] %s", statusIcon, this.taskName);
    }
}
