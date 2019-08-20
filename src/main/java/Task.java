import java.util.UUID;

public abstract class Task {
    protected String taskID;
    protected String taskName;
    protected boolean isCompleted;
    protected String prefix;

    public Task(String taskName) {
        this.taskID = UUID.randomUUID().toString();
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskID() {
        return taskID;
    }

}
