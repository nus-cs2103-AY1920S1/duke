package task;

/**
 * Represents a Task object
 */
public class Task {

    private String name;
    private boolean isComplete;

    /**
     * Creates a new Task object, with completion status set to false by default.
     *
     * @param name          The name of the new task
     */
    Task(String name) {
        this.name = name.trim();
        this.isComplete = false;
    }

    /**
     * Creates a new Task object
     *
     * @param name          The name of the new task
     * @param isComplete    The completion status of the task (true/false)
     */
    Task(String name, boolean isComplete) {
        this.name = name;
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        String status = this.isComplete ? "✓" : "✗";
        return "[" + status + "] " + this.name;
    }

    /**
     * Sets the status of the task to complete
     */
    public void finishTask() {
        this.isComplete = true;
    }

    /**
     * Returns the comprehensive task details to be written to the file.
     *
     * @return the comprehensive task details to be written to the file.
     */
    public String publishTask() {
        String status = this.isComplete ? "1" : "0";
        return status + " | " + this.name;
    }

    /**
     * Returns true if task name contains a keyword
     *
     * @param keyword the keyword to be checked against the task name
     * @return true if task name contains specified keyword
     */
    public boolean containsKeyword(String keyword) {
        return this.name.toLowerCase().contains(keyword);
    }
}