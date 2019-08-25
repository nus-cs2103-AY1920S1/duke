import java.text.ParseException;
import java.util.Date;

public class Event extends Task {
    /**
     * An event task.
     */
    private Date at;

    /**
     * constructor.
     * @param description of task.
     * @param at is the date and time of task.
     * @throws ParseException when input format is different from required.
     */
    Event(String description, String at) throws ParseException {
        super(description);
        this.at = Duke.formatter.parse(at);
    }

    /**
     * get the date and time of the event.
     * @return a string describing the date and time.
     */
    String getAt() {
        return Duke.formatter.format(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Duke.formatter.format(at) + ")";
    }
}
