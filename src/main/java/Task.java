public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean getDoneStatus() {
        return this.isDone;
    }

    protected String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    public String toString() {
        String statusIcon = getStatusIcon();
        return "[" + statusIcon + "] " + this.description;
    }

    public String getStorageFormat() {
        String doneStatusString = this.isDone ? "1" : "0";
        String storageString = "T | " + doneStatusString + " | " + this.description;
        return storageString;
    }
}
