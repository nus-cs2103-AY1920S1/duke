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

    protected LocalDateTime by;
    protected boolean isAllDay;

    /**
     * Deadline Constructor.
     *
     * @param description Description of Deadline task.
     * @param by Deadline datetime.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
        this.isAllDay = false;
    }

    /**
     * Deadline Constructor.
     *
     * @param description Description of Deadline task.
     * @param by Deadline datetime.
     * @param isAllDay Whether task is all day.
     */
    public Deadline(String description, LocalDateTime by, boolean isAllDay) {
        super(description, TaskType.DEADLINE);
        this.by = by;
        this.isAllDay = isAllDay;
    }

    /**
     * Getter for by.
     *
     * @return LocalDateTime representing by.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Setter for by.
     *
     * @param by LocalDateTime representing by.
     */
    public void setBy(LocalDateTime by) {
        this.by = by;
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
                    by.toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        } else {
            return String.format("[%s]%s (by: %s)", TaskType.DEADLINE.getTag(), super.toString(),
                    by.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        }
    }
}