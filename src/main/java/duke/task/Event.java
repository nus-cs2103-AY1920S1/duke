package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Event class indicated with type "E" which contains a start date, start time and end time on top of the other
 * components that Task has.
 */
public class Event extends Task {
    /** Date when the event starts. */
    private LocalDate startDate;
    /** Time when the event starts. */
    private LocalTime startTime;
    /** Time when the event ends. */
    private LocalTime endTime;

    /**
     * Initialises and Event object.
     * @param description Description of the Event.
     * @param startDate Date when the Event starts.
     * @param startTime Time when the Event starts.
     * @param endTime Time when the Event ends.
     */
    public Event(String description, LocalDate startDate, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.type = "E";
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Gets the date when the Event starts.
     * @return Start date of Event.
     */
    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Gets the time when the Event starts.
     * @return Start time of the Event.
     */
    @Override
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Gets the time when the Event ends.
     * @return End time of the Event.
     */
    @Override
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * String of the type followed by status icon, description, start date, start time and end time of the Event.
     * @return String in the given format.
     */
    public String toString() {
        return "[E]" + super.toString() + " (at: " + startTime
                + "-" + endTime + " " + startDate + ")";
    }
}
