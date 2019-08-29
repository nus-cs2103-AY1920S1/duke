import java.lang.reflect.Array;

public class Event extends Task {
    protected Date atDate;
    protected Time atTime;
    protected String exactDuration;

    public Event(String description, String duration) {
        super(description);
        this.exactDuration = duration;
        String[] temp = duration.split(" ");
        atDate = new Date((String) Array.get(temp, 0));
        atTime = new Time((String) Array.get(temp, 1));
    }

    public String getExactDuration() {
        return this.exactDuration;
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