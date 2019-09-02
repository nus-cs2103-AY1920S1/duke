public abstract class Task {
    protected String Description;
    protected boolean isDone;

    public Task(String description , boolean completionStatus) {
        this.Description = description;
        this.isDone = completionStatus;
    }

    public String getCurrentStatus() {
        return ((isDone ? "[✓] " : "[✗] ")); //return tick or X symbols
    }

    public String getOverallStatus() {
        return getCurrentStatus() + Description;
    }
    public void CompleteTask() {
        isDone = true;
    }

    public abstract String encodeForStorage();
}