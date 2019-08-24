package task;

public class Event extends TimedTask {
    public Event(String description, String time) {
        super(description, time);
    }

    @Override
    public String getTaskInitial() {
        return "E";
    }

    @Override
    public String extraText() {
        return " (at: " + this.time + ")";
    }
}
