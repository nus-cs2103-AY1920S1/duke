public abstract class Task {
    protected String taskName;
    protected boolean isCompleted;
    protected String details;
    protected TypeOfTask taskType;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    public String convertTaskToFileString() {
        char isCompleted = this.isCompleted ? 'T' : 'F';
        String taskType = this.taskType.toString();
        String result =  taskType + " | " + isCompleted + " | " + this.taskName + " " + this.details;
        return result.stripTrailing();
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


    @Override
    public String toString() {
        char symbol = this.isCompleted ? '✓' : '✗';
        String result = "[" + taskType + "][" + symbol + "] " + taskName + " " + details;
        return result.stripTrailing();
    }
}
