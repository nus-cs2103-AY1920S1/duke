public class Event extends Task {
    private String time;

    public Event(String des, String time) {
        super(des);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
