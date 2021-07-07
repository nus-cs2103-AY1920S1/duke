import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Represents a deadline, which is a type of task.
 * An deadline has a description and is due by a specific date/time.
 */
public class Deadline extends Task {
    protected String by;
    protected Date byDate;
    protected String day;
    protected String month;
    protected String year;
    protected String hour;
    protected String minute;

    /**
     * Creates a deadline object.
     * Converts the String argument for the deadline date/time into a date object.
     * If the deadline date/time argument is not in the required format, an exception is thrown.
     *
     * @param description The string description of the deadline created.
     * @param by The String of the deadline time/date.
     * @throws InvalidTaskArgumentDukeException if "by" contains invalid information.
     */
    public Deadline(String description, String by) throws InvalidTaskArgumentDukeException {
        super(description);
        this.by = by;

        String[] dateTime = by.split(" ");
        String[] date = dateTime[0].split("/");

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try {
            this.day = date[0];
            this.month = date[1];
            this.year = date[2];
            this.hour = dateTime[1].substring(0, 2);
            this.minute = dateTime[1].substring(2);

            this.byDate = format.parse(day + "/" + month + "/" + year + " " + hour + ":" + minute);
        } catch (Exception e) {
            throw new InvalidTaskArgumentDukeException("OOPS!!! The format of deadline timing is invalid.");
        }

    }

    /**
     * Converts the deadline's date object into the required format.
     *
     * @return The String of the deadline's date/time in the required format.
     */
    public String dateToString() {
        SimpleDateFormat format;

        if (this.day.equals("1")) {
            format = new SimpleDateFormat("d" + "'st of '" + "MMMM yyyy" + "', '" + "hh:mma");
        } else if (this.day.equals("2")) {
            format = new SimpleDateFormat("d" + "'nd of '" + "MMMM yyyy" + "', '" + "hh:mma");
        } else if (this.day.equals("3")) {
            format = new SimpleDateFormat("d" + "'rd of '" + "MMMM yyyy" + "', '" + "hh:mma");
        } else {
            format = new SimpleDateFormat("d" + "'th of '" + "MMMM yyyy" + "', '" + "hh:mma");
        }

        return format.format(this.byDate);
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateToString() + ")";
    }
}
