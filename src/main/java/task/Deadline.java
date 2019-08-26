package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Deadline Class.
 *
 * Represents the deadline-type task.
 *
 * @author Marcus Ong
 */
public class Deadline extends Task {

    protected LocalDateTime by;
    protected boolean isAllDay;

    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
        this.isAllDay = false;
    }

    public Deadline(String description, LocalDateTime by, boolean isAllDay) {
        super(description, TaskType.DEADLINE);
        this.by = by;
        this.isAllDay = isAllDay;
    }

    public LocalDateTime getBy() {
        return by;
    }

    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    public boolean isAllDay() {
        return isAllDay;
    }

    public void setAllDay(boolean allDay) {
        isAllDay = allDay;
    }

    @Override
    public String toString() {
        if (isAllDay) {
            return String.format("[%s]%s (by: %s)", TaskType.DEADLINE.getTag(), super.toString(),
                    by.toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        } else {
            return String.format("[%s]%s (by: %s)", TaskType.DEADLINE.getTag(), super.toString(),
                    by.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        }
    }
}