import java.time.LocalDateTime;

public class Event extends Task{
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, LocalDateTime start, LocalDateTime end, boolean done) {
        super(description, done);
        this.start = start;
        this.end = end;
    }

    @Override
    public Event finish() {
        return new Event(description, start, end,true);
    }

    @Override
    public String toString() {
        TimeManager tm = new TimeManager();
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.description + " (from: " + tm.printTime((start))
                + ", to " + tm.printTime(end)+ ")\n";
    }
}
