package duke.task;

public abstract class Task {
    String taskDetails;
    boolean completed;
    Task(String taskDetails) {
        this.taskDetails = taskDetails;
        this.completed = false;
    }

    void taskDone() {
        this.completed = true;
    }

    abstract String saveInfo();

    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * A getter for task details.
     *
     * @return the task details.
     */
    public String getTaskDetails() {
        return this.taskDetails;
    }
    @Override
    public String toString() {
        if (completed) {
            return "[✓] " + this.taskDetails;
        } else {
            return "[✗] " + this.taskDetails;
        }
    }
}
