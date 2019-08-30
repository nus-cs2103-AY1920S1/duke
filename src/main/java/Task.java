public class Task {
    protected String task;
    protected String status; // tick or not
    protected boolean isMarked = false;

    public Task (String task) {
        this.task = task;
        this.status = "\u2718";
    }

    public void markAsDone() {
        this.status = "\u2713";
        this.isMarked = true;
    }

    public int isDone() {
        return this.isMarked ? 1 : 0;
    }

    public String getTask() {
        return this.task;
    }
    @Override
    public String toString() {
        return "[" + this.status + "] " + this.task;
    }
}
