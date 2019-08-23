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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatter.format(startdateTime) + " - " + endTime + ")";
    }
}
