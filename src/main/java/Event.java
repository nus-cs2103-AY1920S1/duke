public class Event extends Task {
    private String time;
    public Event(String name, boolean isDone, String time) {
        super(name, isDone);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getName() + " (at: " + time  + ")";
    }
}
