package duck.task;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Event extends Task {

    private LocalDateTime startDateTime;
    private LocalTime endTime;

    public Event(String description, LocalDateTime startdateTime, LocalTime endTime) {
        super(description);
        this.startDateTime = startdateTime;
        this.endTime = endTime;
    }

    public Event(String description, LocalDateTime startDateTime, LocalTime endTime, boolean isDone) {
        super(description, isDone);
        this.startDateTime = startDateTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatter.format(startDateTime) + " - " + endTime + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event another = (Event) obj;
            return this.description.equals(another.description) && this.startDateTime.equals(another.startDateTime)
                    && this.endTime.equals(another.endTime) && this.isDone == another.isDone;
        } else {
            return false;
        }
    }
}
