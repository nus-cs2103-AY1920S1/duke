public abstract class Task {
    protected String taskName;
    protected boolean isCompleted;
    protected String prefix;
    protected String details;
    protected TypeOfTask taskType;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public String convertTaskToFileString() {
        char isCompleted = this.isCompleted ? 'T' : 'F';
        String taskType = this.taskType.toString();
        return taskType + " | " + isCompleted + " | " + this.taskName + " " + this.details;
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
