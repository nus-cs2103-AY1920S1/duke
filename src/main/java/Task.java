public class Task {
    protected String task;
    protected String status;

    public Task (String task) {
        this.task = task;
        this.status = "\u2718";
    }

    public void markAsDone() {
        this.status = "\u2713";
    }

    @Override
    public String toString() {
        return "[" + this.status + "] " + this.task;
    }
}
