import java.time.LocalDateTime;

public class Event extends Task{
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.startTime = start;
        this.endTime = end;
    }

    public Event(String description, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(description, isDone);
        this.startTime = start;
        this.endTime = end;
    }

    @Override
    public Event finish() {
        return new Event(description, startTime, endTime,true);
    }

    @Override
    public String toString() {
        TimeManager tm = new TimeManager();
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.description + " (from: " + tm.printTime(startTime)
                + " , to " + tm.printTime(endTime)+ " )\n";
    }
}
