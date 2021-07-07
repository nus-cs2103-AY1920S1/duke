import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an event, which is a type of task.
 * An event has a description and starts at a specific date/time.
 */
public class Event extends Task {
    protected String at;
    protected Date atDate;
    protected String day;
    protected String month;
    protected String year;
    protected String hour;
    protected String minute;

    /**
     * Creates an event object.
     * Converts the String argument for the event date/time into a date object.
     * If the event date/time argument is not in the required format, an exception is thrown.
     *
     * @param description The string description of the event created.
     * @param at The String of the event time/date.
     * @throws InvalidTaskArgumentDukeException if "at" contains invalid information.
     */
    public Event(String description, String at) throws InvalidTaskArgumentDukeException {
        super(description);
        this.at = at;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try {
            String[] dateTime = at.split(" ");
            String[] date = dateTime[0].split("/");
            this.day = date[0];
            this.month = date[1];
            this.year = date[2];
            this.hour = dateTime[1].substring(0, 2);
            this.minute = dateTime[1].substring(2);

            this.atDate = format.parse(day + "/" + month + "/" + year + " " + hour + ":" + minute);
        } catch (Exception e) {
            throw new InvalidTaskArgumentDukeException("OOPS!!! The format of event timing is invalid.");
        }

    }

    /**
     * Converts the event's date object into the required format.
     *
     * @return The String of the event's date/time in the required format.
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

        return format.format(this.atDate);
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateToString() + ")";
    }
}
