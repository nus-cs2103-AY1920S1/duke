package duke.task;

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

    abstract String saveInfo();

    @Override
    public String toString() {
        if (completed) {
            return "[✓] " + this.taskDetails;
        } else {
            return "[✗] " + this.taskDetails;
        }
    }
}
