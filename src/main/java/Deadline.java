import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.InputMismatchException;

public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /** Deadline constructor.
     *
     * @param description is the description of the Task
     * @param date is the date of Task
     * @param time is the time of Task
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * genDeadlineTask() will convert input cmd into Deadline Task.
     *
     * @param cmd is the string input by user
     * @return Deadline Task
     */
    public static Deadline genDeadlineTask(String cmd) {
        try {
            String descriptionAndTime = cmd.substring(9);
            String[] details = descriptionAndTime.split(" /by");
            String[] dateTime = details[1].trim().split(" ", 2);
            String desc = details[0];
            LocalDate date = LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern("d/MM/yyy"));
            LocalTime time = LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern("HHmm"));
            return new Deadline(desc, date, time);

        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("The description of a Deadline cannot be empty.");

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("The input for Deadline is invalid. "
                    + "Please ensure you input <description>, <Date> in d/MM/yyyy format, "
                    + "and <Time> in HHmm format.");
        } catch (NumberFormatException e) {
            throw new DukeIllegalArgumentException("Illegal input for the date/time. "
                    + "Please input date in d/MM/yyyy format and time in HHmm format.");
        }
    }

    @Override
    public String toDataFormat() {
        return "D | " + super.toDataFormat() + " | " + date.format(DateTimeFormatter.ofPattern("d/MM/yyyy"))
                + ", " + time.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + ", " + time.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }

}
