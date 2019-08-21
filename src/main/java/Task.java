public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void addToArray(Task t, Task[] arr, int index) {
        arr[index] = t;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}