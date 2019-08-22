public class Task {

    protected String taskName;
    protected boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public void markAsDone() {
        done = true;
    }

    public String toString() {
        if (done) {
            return "[✓]" + taskName;
        } else {
            return "[✗]" + taskName;
        }
    }
}

