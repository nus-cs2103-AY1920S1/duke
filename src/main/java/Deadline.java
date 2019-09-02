public class Deadline extends Task {
    protected String time;


    public Deadline(String name, String time) {
        this.name = name;
        this.time = time;
        this.isDone = false;
    }

    public String toFile() {
        if(isDone) {
            return "D-1-" + name + "-" + time;
        } else {
            return "D-0-" + name + "-" + time;
        }
    }

    public String toString() {
        if (isDone) {
            return "[D][✓] " + name + " (by: " + time + ")";
        } else {
            return "[D][✗] " + name + " (by: " + time + ")";
        }
    }
}