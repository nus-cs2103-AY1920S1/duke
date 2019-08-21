public class Task {
    protected String task_name;
    protected boolean isDone;

    public Task(String task_name) {
        this.task_name = task_name;
        this.isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return task_name;
    }
}
