package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task which has a deadline.
 *
 */
public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;
    private static DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter INPUT_TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");
    private static DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy");
    private static DateTimeFormatter OUTPUT_TIME_FORMAT = DateTimeFormatter.ofPattern("h:mm a");

    /**
     * Constructor class for Deadline object.
     *
     * @param desc Description of Deadline.
     * @param date Date of Deadline in localDate representation
     * @param time Time of Deadline in localTime representation
     */
    public Deadline(String desc, LocalDate date, LocalTime time) {
        super(desc);
        this.date = date;
        this.time = time;
    }

    /**
     * Constructor to instantiate a deadline object.
     *
     * @param desc description of deadline
     * @param date date of deadline in format dd/MM/yyyy
     * @param time time of deadline in format HHmm
     * @throws DateTimeParseException For invalid datetime format.
     */
    public Deadline(String desc, String date, String time) throws DateTimeParseException {
        super(desc);
        this.date = LocalDate.parse(date, INPUT_DATE_FORMAT);
        this.time = LocalTime.parse(time, INPUT_TIME_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + date.format(OUTPUT_DATE_FORMAT)
                + ", " + time.format(OUTPUT_TIME_FORMAT) + ")";
    }

    @Override
    public String fileFormat() {
        return String.format("D | %s | %s | %s %s", isDoneString(), getDescription(),
                date.format(INPUT_DATE_FORMAT),
                time.format(INPUT_TIME_FORMAT));
    }
}
