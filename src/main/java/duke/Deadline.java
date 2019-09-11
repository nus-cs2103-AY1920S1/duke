import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a deadline, with a given date/time/both 
 * which indicates when the user needs to complete it by.
 */
class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;

    /**
     * Constructor for Deadline, which converts the dateTime String into 
     * a LocalDate, LocalTime or both.
     * 
     * @param task A String of the user's inputted task.
     * @param dateTime A String of the user's inputted date, time, or both.
     * @throws DukeException When the user inputs a illegitamate date, time, both or wrong format.
     */
    public Deadline(String task, LocalDate date, LocalTime time) throws DukeException {
        super(task);
        assert date != null || time != null : "Both date and time are null";
        this.date = date;
        this.time = time;
    }

    /**
     * Returns a String of the deadline, which includes the date, time or both.
     * 
     * @return a String of the deadline, which includes the date, time or both.
     */
    @Override
    public String toString() {
        assert this.date != null || this.time != null : "Both date and time are invalid";
        if (this.date == null) {
            return "[D]" + super.toString() + " (by: " + this.time.format(TIME_FORMATTER) + ")";
        } else if (this.time == null) {
            return "[D]" + super.toString() + " (by: " + this.date.format(DATE_FORMATTER) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + this.date.format(DATE_FORMATTER) 
                    + " " + this.time.format(TIME_FORMATTER) + ")";
        }
    }
}