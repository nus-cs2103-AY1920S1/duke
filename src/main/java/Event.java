import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Event extends Task {

    private LocalDateTime startdateTime;
    private LocalTime endTime;

    public Event(String description, LocalDateTime startdateTime, LocalTime endTime) {
        super(description);
        this.startdateTime = startdateTime;
        this.endTime = endTime;
    }

    public Event(String description, String duration, boolean isDone) {
        super(description, isDone);
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatter.format(startdateTime) + " - " + endTime + ")";
    }
}
