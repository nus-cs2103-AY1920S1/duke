public class Task {
    private int taskID;
    private String taskName;
    private boolean isCompleted;

    public Task(int taskID, String taskName) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.isCompleted = false;
    }


    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        char symbol = isCompleted ? '✓' : '✗';
        return "\t " + taskID + ".[" + symbol + "] " + taskName;
    }
}
