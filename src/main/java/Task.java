import java.util.Date;

public abstract class Task {
    protected String taskName;
    protected boolean isCompleted;
    protected String prefix;
    protected String details;
    protected Date time;


    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDetails() {
        return details;
    }
}
