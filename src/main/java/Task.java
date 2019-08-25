public class Task {
    public static final String TICK = "\u2713";
    public static final String CROSS = "\u2718";
    protected String name;
    protected boolean done;
    protected String type;

    public Task() {

    }

    public Task(String type, String name) {
        this.type = type;
        this.name = name;
        this.done = false;
    }

    public Task(String type, String done, String name) {
        this.type = type;
        this.name = name;
        if (done.equals("1")) {
            this.done = true;
        } else {
            this.done = false;
        }
    }

    public String done() {
        this.done = true;
        return String.format("Nice! I've marked this task as done:\n  [%s] %s", "v", name);
    }

    public String fileFormat() {
        return String.format("%s | %s | %s\n", type, done ? "1" : "0", name);
    }

    public String toString() {
        return String.format("[%s][%s] %s", type, done ? "v" : "x", name);
    }
}
