public class Task {
    private char status;
    private String taskName;

    public Task(String taskName) {
        this.status = '✗';
        this.taskName = taskName;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus() {
        status = '✓';
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
