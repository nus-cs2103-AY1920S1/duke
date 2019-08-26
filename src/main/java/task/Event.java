package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Event Class.
 *
 * Represents the event-type task.
 *
 * @author Marcus Ong
 */
public class Event extends Task {

    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;
    protected boolean isAllDay;

    public Event(String description, LocalDateTime startDateTime) {
        super(description, TaskType.EVENT);
        this.startDateTime = startDateTime;
        this.endDateTime = startDateTime;
        this.isAllDay = false;
    }

    public Event(String description, LocalDateTime startDateTime, boolean isAllDay) {
        super(description, TaskType.EVENT);
        this.startDateTime = startDateTime;
        this.endDateTime = startDateTime;
        this.isAllDay = isAllDay;
    }

    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description, TaskType.EVENT);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isAllDay = false;
    }

    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime,
                 boolean isAllDay) {
        super(description, TaskType.EVENT);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isAllDay = isAllDay;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public boolean isAllDay() {
        return isAllDay;
    }

    public void setAllDay(boolean allDay) {
        isAllDay = allDay;
    }

    @Override
    public String toString() {
        if (isAllDay && startDateTime.toLocalDate().isEqual(endDateTime.toLocalDate())) {
            return String.format("[%s]%s (at: %s)", TaskType.EVENT.getTag(), super.toString(),
                    startDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        } else if (isAllDay){
            return String.format("[%s]%s (at: %s - %s)", TaskType.EVENT.getTag(), super.toString(),
                    startDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)),
                    endDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        } else if (startDateTime.toLocalDate().isEqual(endDateTime.toLocalDate())) {
            return String.format("[%s]%s (at: %s)", TaskType.EVENT.getTag(), super.toString(),
                    startDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        } else {
            return String.format("[%s]%s (at: %s - %s)", TaskType.EVENT.getTag(), super.toString(),
                    startDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)),
                    endDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        }
    }
}