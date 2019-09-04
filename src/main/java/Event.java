import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /** Event constructor.
     *
     * @param description is the description of the Task
     * @param date is the date of Task
     * @param time is the time of Task
     */
    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * genEventTask() will convert input cmd into Event Task.
     *
     * @param cmd is the string input by user
     * @return Event Task
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
