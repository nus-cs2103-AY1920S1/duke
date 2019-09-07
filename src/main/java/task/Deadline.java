package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Deadline Class.
 *
 * <p>Represents the deadline-type task.
 *
 * @author Marcus Ong
 */
public class Deadline extends Task {

    protected LocalDateTime byDateTime;
    protected boolean isAllDay;

    /**
     * Deadline Constructor.
     *
     * @param description Description of Deadline task.
     * @param byDateTime Deadline datetime.
     */
    public Deadline(String description, LocalDateTime byDateTime) {
        super(description, TaskType.DEADLINE);
        this.byDateTime = byDateTime;
        this.isAllDay = false;
    }

    /**
     * Deadline Constructor.
     *
     * @param description Description of Deadline task.
     * @param byDateTime Deadline datetime.
     * @param isAllDay Whether task is all day.
     */
    public Deadline(String description, LocalDateTime byDateTime, boolean isAllDay) {
        super(description, TaskType.DEADLINE);
        this.byDateTime = byDateTime;
        this.isAllDay = isAllDay;
    }

    /**
     * Getter for by.
     *
     * @return LocalDateTime representing by.
     */
    public LocalDateTime getByDateTime() {
        return byDateTime;
    }

    /**
     * Setter for by.
     *
     * @param byDateTime LocalDateTime representing by.
     */
    public void setByDateTime(LocalDateTime byDateTime) {
        this.byDateTime = byDateTime;
    }

    /**
     * Getter for isAllDay.
     *
     * @return boolean isAllDay.
     */
    public boolean isAllDay() {
        return isAllDay;
    }

    /**
     * Setter for isAllDay.
     *
     * @param allDay boolean isAllDay.
     */
    public void setAllDay(boolean allDay) {
        isAllDay = allDay;
    }

    @Override
    public String toString() {
        if (isAllDay) {
            return String.format("[%s]%s (by: %s)", TaskType.DEADLINE.getTag(), super.toString(),
                    byDateTime.toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        } else {
            return String.format("[%s]%s (by: %s)", TaskType.DEADLINE.getTag(), super.toString(),
                    byDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        }
    }
}