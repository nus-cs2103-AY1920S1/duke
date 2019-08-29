import java.lang.reflect.Array;

public class Event extends Task {
    protected Date atDate;
    protected Time atTime;

    public Event(String description, String duration) {
        super(description);
        String[] temp = duration.split(" ");
        atDate = new Date((String) Array.get(temp, 0));
        atTime = new Time((String) Array.get(temp, 1));
    }

    public String getDuration() {
        return this.duration;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.atDate + ", " + this.atTime +")";
    }
}