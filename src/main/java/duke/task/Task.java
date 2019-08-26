package duke.task;

public class Task {

    protected String taskName;
    protected boolean isDone;

    public Task(String name) {
        this.isDone = false;
        this.taskName = name;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getType() ,isDone ? "\u2713": "\u2718", taskName );
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getType() {
        return " ";
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getTaskName() {
        return this.taskName;
    }
}
