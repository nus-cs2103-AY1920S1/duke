import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Event extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructs an Event with description, date, time.
     *
     * @param description of the Event.
     * @param date the date that the Event happens at.
     * @param time the time that the Event happens at.
     */
    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Factory method to construct Deadline given the user input.
     *
     * @param cmd the string input by user.
     * @return Event object.
     */
    public static Event genEventTask(String cmd) {
        try {
            String descriptionAndTime = cmd.substring(6);
            String[] details = descriptionAndTime.split(" /at");
            String[] dateTime = details[1].trim().split(" ", 2);
            String desc = details[0];
            LocalDate date = LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern("d/MM/yyy"));
            LocalTime time = LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern("HHmm"));
            return new Event(desc, date, time);

        } catch (DateTimeParseException e) {
            throw new DukeIllegalArgumentException("The input for Deadline is invalid. "
                    + "Please ensure you input <description>, <Date> in d/MM/yyyy format, "
                    + "and <Time> in HHmm format.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("The description of an Event cannot be empty.");

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("The input for Event is invalid. "
                    + "Please ensure you input <description>, <Date> in d/MM/yyyy format, "
                    + "and <Time> in HHmm format.");
        } catch (NumberFormatException e) {
            throw new DukeIllegalArgumentException("Illegal input for the date/time. "
                    + "Please input date in d/MM/yyyy format"
                    + "and time in HHmm format.");
        }
    }

    /**
     * Stringifies the Event in the format specified when writing to storage file.
     *
     * @return string that is to be written to storage file.
     */
    @Override
    public String toDataFormat() {
        return "E | " + super.toDataFormat() + " | " + date.format(DateTimeFormatter.ofPattern("d/MM/yyyy"))
                + ", " + time.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + ", " + time.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }
}
