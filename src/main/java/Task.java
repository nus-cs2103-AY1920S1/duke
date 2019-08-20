public class Task {
    public static final String TICK = "\u2713";
    public static final String CROSS = "\u2718";
    private int index;
    private String name;
    private boolean done;

    public Task(String name, int index) {
        this.name = name;
        this.done = false;
        this.index = index;
    }

    public String done() {
        this.done = true;
        return String.format("Nice! I've marked this task as done:\n  [%s] %s", "v", name);
    }

    public String toString() {
        return String.format("%d.[%s] %s", index, done ? "v" : "x", name);
    }
}
