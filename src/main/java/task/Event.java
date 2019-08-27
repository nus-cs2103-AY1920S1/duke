package task;

import exception.DukeIllegalArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Event Class.
 *
 * <p>Represents the event-type task.
 *
 * @author Marcus Ong
 */
public class Event extends Task {

    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;
    protected boolean isAllDay;

    /**
     * Event constructor.
     *
     * @param description Description of Event task.
     * @param startDateTime Start Datetime of event.
     */
    public Event(String description, LocalDateTime startDateTime) {
        super(description, TaskType.EVENT);
        this.startDateTime = startDateTime;
        this.endDateTime = startDateTime;
        this.isAllDay = false;
    }

    /**
     * Event constructor.
     *
     * @param description Description of Event task.
     * @param startDateTime Start Datetime of event.
     * @param isAllDay Whether task is all day.
     */
    public Event(String description, LocalDateTime startDateTime, boolean isAllDay) {
        super(description, TaskType.EVENT);
        this.startDateTime = startDateTime;
        this.endDateTime = startDateTime;
        this.isAllDay = isAllDay;
    }

    /**
     * Event constructor.
     *
     * @param description Description of Event task.
     * @param startDateTime Start Datetime of event.
     * @param endDateTime End Datetime of event.
     * @throws DukeIllegalArgumentException If end date is before start date.
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) throws
            DukeIllegalArgumentException {
        super(description, TaskType.EVENT);
        if (endDateTime.isBefore(startDateTime)) {
            throw new DukeIllegalArgumentException("End date should be before start date!");
        }
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isAllDay = false;
    }

    /**
     * Description of Event task.
     *
     * @param description Description of Event task.
     * @param startDateTime Start Datetime of event.
     * @param endDateTime End Datetime of event.
     * @param isAllDay Whether task is all day.
     * @throws DukeIllegalArgumentException If end date is before start date.
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime,
                 boolean isAllDay) throws DukeIllegalArgumentException {
        super(description, TaskType.EVENT);
        if (endDateTime.isBefore(startDateTime)) {
            throw new DukeIllegalArgumentException("End date should be before start date!");
        }
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isAllDay = isAllDay;
    }

    /**
     * Getter for startDateTime.
     *
     * @return LocalDateTime object representing startDateTime.
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Setter for startDateTime.
     *
     * @param startDateTime LocalDateTime to set startDateTime.
     * @throws DukeIllegalArgumentException If end date is before start date.
     */
    public void setStartDateTime(LocalDateTime startDateTime) throws DukeIllegalArgumentException {
        if (this.endDateTime.isEqual(this.startDateTime)) {
            this.endDateTime = startDateTime;
        }
        if (this.endDateTime.isBefore(startDateTime)) {
            throw new DukeIllegalArgumentException("End date should be before start date!");
        }
        this.startDateTime = startDateTime;
    }

    /**
     * Getter for endDateTime.
     *
     * @return LocalDateTime object representing endDateTime.
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Setter for startDateTime.
     *
     * @param endDateTime LocalDateTime to set endDateTime.
     * @throws DukeIllegalArgumentException If end date is before start date.
     */
    public void setEndDateTime(LocalDateTime endDateTime) throws DukeIllegalArgumentException {
        if (endDateTime.isBefore(this.startDateTime)) {
            throw new DukeIllegalArgumentException("End date should be before start date!");
        }
        this.endDateTime = endDateTime;
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
        if (isAllDay && startDateTime.toLocalDate().isEqual(endDateTime.toLocalDate())) {
            return String.format("[%s]%s (at: %s)", TaskType.EVENT.getTag(), super.toString(),
                    startDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        } else if (isAllDay) {
            return String.format("[%s]%s (at: %s - %s)", TaskType.EVENT.getTag(), super.toString(),
                    startDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)),
                    endDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        } else if (startDateTime.isEqual(endDateTime)) {
            return String.format("[%s]%s (at: %s)", TaskType.EVENT.getTag(), super.toString(),
                    startDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        } else if (startDateTime.toLocalDate().isEqual(endDateTime.toLocalDate())) {
            return String.format("[%s]%s (at: %s - %s)", TaskType.EVENT.getTag(), super.toString(),
                    startDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)),
                    endDateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)));
        } else {
            return String.format("[%s]%s (at: %s - %s)", TaskType.EVENT.getTag(), super.toString(),
                    startDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)),
                    endDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        }
    }
}