public class Event extends Task {
    protected String time;


    public Event(String name, String time) {
        this.name = name;
        this.time = time;
        this.isDone = false;
    }

    public String toString() {
        if(isDone) {
            return "[E}[✓] " + name + " (at: " + time + ")";
        } else {
            return "[E}[✗] " + name + " (at: " + time + ")";
        }
    }
}
