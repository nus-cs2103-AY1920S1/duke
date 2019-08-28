package task;

/**
 * Represents a Task object
 */
public class Task {

    private String name;
    private boolean isComplete;

    Task(String name) {
        this.name = name.trim();
        this.isComplete = false;
    }

    Task(String name, boolean isComplete) {
        this.name = name;
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        String status = this.isComplete ? "✓" : "✗";
        return "[" + status + "] " + this.name;
    }

    public void finishTask() { this.isComplete = true; }
    public String publishTask() {
        String status = this.isComplete ? "1" : "0";
        return status + " | " + this.name;
    }

}