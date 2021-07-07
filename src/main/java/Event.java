import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Event extends Task {

    protected Date start;
    protected Date end;

    /**
     * Constructor for event class.
     *
     * @param description content of event
     * @param start date and start time of event
     * @param end end time of event
     */
    public Event(String description, Date start, Date end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        SimpleDateFormat startFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat endFormat = new SimpleDateFormat(" - HH:mm");
        return "[E]" + super.toString() + "(at: " + startFormat.format(start) + endFormat.format(end) + ")";
    }


    @Override
    public int hashCode() {
        return Objects.hash(this.description, this.start, this.end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Event test = (Event) o;
        return this.description.equals(test.description) &&
                this.start.equals(test.start) &&
                this.end.equals(test.end);
    }
}