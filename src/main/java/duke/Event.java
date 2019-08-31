import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

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
     * @param dateTime A String of the user's inputted date, time, or both.
     * @throws DukeException When the user inputs a illegitamate date, time, both or wrong format.
     */
    public Event(String task, String dateTime) throws DukeException {
        super(task);
        String[] dateTimeArr = dateTime.split(" ", 2);
        if (dateTimeArr.length == 1) {
            try {
                this.date = LocalDate.parse(dateTimeArr[0], DATE_FORMATTER);
            } catch (Exception e) {
                try {
                    this.time = LocalTime.parse(dateTimeArr[0], TIME_FORMATTER);
                } catch (DateTimeParseException error) {
                    throw new DukeException("Please enter a legitamate date, time or both");
                } catch (Exception error) {
                    throw new DukeException("The format entered is wrong. Use \'help\' "
                            + "for formatting styles");
                }
            }
        } else {
            try {
                this.date = LocalDate.parse(dateTimeArr[0], DATE_FORMATTER);
                this.time = LocalTime.parse(dateTimeArr[1], TIME_FORMATTER);
            } catch (DateTimeParseException error) {
                throw new DukeException("Please enter a legitamate date, time or both");
            } catch (Exception error) {
                throw new DukeException("The format entered is wrong. Use \'help\' "
                        + "for formatting styles");
            }
        }
    }

    /**
     * Returns a String of the Event, which includes the date, time or both.
     * 
     * @return a String of the Event, which includes the date, time or both.
     */
    @Override
    public String toString() {
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