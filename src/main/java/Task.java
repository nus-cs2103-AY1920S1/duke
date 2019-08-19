public class Task {

    public static int count;

    protected int num;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.num = ++count;
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString() {
        return num + ".[" + getStatusIcon() + "] " + description;
    }

    public String done() {
        return "  [✓] " + description;
    }
}
