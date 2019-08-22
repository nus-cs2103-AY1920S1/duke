abstract class Task {
    String taskDetails;
    boolean completed;
    Task(String taskDetails) {
        this.taskDetails = taskDetails;
        this.completed = false;
    }

    void taskDone() {
        this.completed = true;
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
