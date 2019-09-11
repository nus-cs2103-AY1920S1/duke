import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents an Event, with a given date/time/both which indicates when the event will happen.
 */
class Event extends Task {
    private LocalDate date;
    private LocalTime time;

    /**
     * Constructor for Event, which converts the dateTime String into a LocalDate, LocalTime or both.
     * 
     * @param task A String of the user's inputted task.
     * @param date The date of the event - can be null
     * @param time The time of the event - can be null;
     * @throws DukeException When the user inputs a illegitamate date, time, both or wrong format.
     */
    public Event(String task, LocalDate date, LocalTime time) throws DukeException {
        super(task);
        assert date != null || time != null : "Both date and time are null";
        this.date = date;
        this.time = time;
    }

    /**
     * Returns a String of the Event, which includes the date, time or both.
     * 
     * @return a String of the Event, which includes the date, time or both.
     */
    @Override
    public String toString() {
        assert this.date != null || this.time != null : "Both date and time are invalid";
        if (this.date == null) {
            return "[E]" + super.toString() + " (at: " + this.time.format(TIME_FORMATTER) + ")";
        } else if (this.time == null) {
            return "[E]" + super.toString() + " (at: " + this.date.format(DATE_FORMATTER) + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + this.date.format(DATE_FORMATTER) 
                    + " " + this.time.format(TIME_FORMATTER) + ")";
        }
    }
}