public class Event extends Task {
    protected String time;


    public Event(String name, String time) {
        this.name = name;
        this.time = time;
        this.isDone = false;
    }

    public String toFile() {
        if(isDone) {
            return "E-1-" + name + "-" + time;
        } else {
            return "E-0-" + name + "-" + time;
        }
    }

    public String toString() {
        if (isDone) {
            return "[E][✓] " + name + " (at: " + time + ")";
        } else {
            return "[E][✗] " + name + " (at: " + time + ")";
        }
    }
}
