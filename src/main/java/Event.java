public class Event extends Task {
    private String time;

    public Event(String description) {
        super(description);
        this.type = "E";
        this.time = "";
    }

    public void setTime(String time) {
        this.time = " (at: " + time.substring(3) + ")";
    }

    @Override
    public String toString() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.description + this.time;
    }
}
