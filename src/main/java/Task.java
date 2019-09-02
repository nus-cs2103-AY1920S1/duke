public class Task {
    protected String task;
    protected boolean isDone;

    public Task (String task) {
        this.task = task;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns tick if the task is done,
     * otherwise returns X.
     * @return Tick or X string symbol
     */

    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    public int isDone() {
        return this.isDone ? 1 : 0;
    }

    public String getTask() {
        return this.task;
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.task;
    }
}
