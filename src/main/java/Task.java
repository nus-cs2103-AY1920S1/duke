public class Task {
    boolean completed = false;
    String task;

    public Task(String task) {
        this.task = task;
    }

    public void complete() {
        this.completed = true;
    }

    @Override
    public String toString() {
        if (completed) {
            return "[✓] " + this.task;
        } else {
            return "[✗] " + this.task;
        }
    }
}
