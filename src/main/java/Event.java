public class Event extends Task {
    private String time;

    public Event(String des, String time) {
        super(des);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
