public class Deadline extends Task {
    protected String time;


    public Deadline(String name, String time) {
        this.name = name;
        this.time = time;
        this.isDone = false;
    }

    public String toString() {
        if (isDone) {
            return "[D}[✓] " + name + " (by: " + time + ")";
        } else {
            return "[D}[✗] " + name + " (by: " + time + ")";
        }
    }
}