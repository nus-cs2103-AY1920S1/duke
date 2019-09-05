package duke.tasklist;

public class Task {
    private String description;
    private boolean isCompleted;

    /**
     * Constructor method of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Method to get description of a Task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method to set task to completed.
     */
    public void setCompleted() {
        this.isCompleted = true;
    }

    /**
     * Method to check completed status of task.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Method to get checkmark or cross depending on completion status of task.
     */
    public String getMark() {
        if (isCompleted) {
            return "✓";
        } else {
            return "✗";
        }
    }

    /**
     * toString method of Task.
     */
    @Override
    public String toString() {
        return "[" + this.getMark() + "] " + this.getDescription() + "\n";
    }

    /**
     * Dummy method to format tasks into correct format to be saved.
     */
    public String saveString() {
        return "";
    }
}
