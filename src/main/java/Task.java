public class Task {

    protected String taskName;
    protected boolean done;

    public Task(String taskName, boolean done) {
        this.taskName = taskName;
        this.done = done;
    }

    public void markAsDone() {
        done = true;
    }

    public String getTaskName() {
        return taskName;
    }

    public String toString() {
        if (done) {
            return "[✓]" + taskName;
        } else {
            return "[✗]" + taskName;
        }
    }

    public String storageFormat() {
        return "";
    }
}

