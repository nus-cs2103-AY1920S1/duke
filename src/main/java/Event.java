import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

class Event extends Task {
    private Date time;

    /**
     * Creates a new Event with the given description and timing.
     * @param description       Description of Event.
     * @param time              Timing of the Event.
     */
    Event(String description, String time) {
        super(description);
        parse(time);
    }

    /**
     * Sets the timing of the current event to the date represented in the
     * given String. If the input format is invalid, event time is set to the
     * current instant.
     * @param date  Valid date string, as specified in DukeFormatter.DATE_FORMATS.
     */
    private void parse(String date) {
        for (SimpleDateFormat format : DukeFormatter.DATE_FORMATS) {
            if (time != null) {
                break;
            }
            try {
                time = format.parse(date);
            } catch (ParseException e) {
                // do nothing and try the next format
            }
        }
        if (time == null) {
            time = new Date();
        }
    }

    /**
     * Creates a new Event with the given description, timing and status.
     * @param description       Description of Event.
     * @param time              Timing of the Event.
     * @param isDone            Whether the Event is done or not.
     */
    Event(String description, String time, boolean isDone) {
        super(description, isDone);
        parse(time);
    }

    /**
     * Returns the letter "E", representing the type Event.
     * @return  "E"
     */
    @Override
    String getType() {
        return "E";
    }

    /**
     * Returns a representation of the current Event, including its time, in
     * an appropriate format for data storage.
     * @return  String representing the current Event.
     */
    @Override
    String formatAsData() {
        return super.formatAsData() + " | "
                + String.format("%1$ta, %1$td %1$tb %1$ty, %1$tR", time);
    }

    /**
     * Returns a string containing the type of Task, done status, description,
     * and time.
     * @return  String describing the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + String.format("%1$ta, %1$td %1$tb %1$ty, %1$tR", time) + ")";
    }
}
