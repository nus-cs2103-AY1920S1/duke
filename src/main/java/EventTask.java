import java.util.Date;
import java.text.SimpleDateFormat;

public class EventTask extends Task {
    private static final SimpleDateFormat DATE_WRITER = new SimpleDateFormat("dd/MM/yyyy HHmm");
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy, h:mm a");

    protected Date atTime;

    /**
     *  Constructs an <code>EventTask</code> object with a given description and event timestamp.
     *  @param description <code>String</code> description of this <code>Task</code>.
     *  @param atTime timestamp of this <code>EventTask</code>, as a <code>Date</code> object.
     */
    public EventTask(String description, Date atTime) {
        super(description);
        this.atTime = atTime;
    }

    /**
     *  Returns the timestamp of this <code>EventTask</code>.
     *  @return timestamp of this <code>EventTask</code>, as a <code>Date</code>.
     */
    public Date getTime() {
        return this.atTime;
    }

    /**
     *  {@inheritDoc}
     */
    public String toEncodedString() {
        return String.format(
            "E | %d | %s | %s",
            this.isComplete ? 1 : 0,
            this.description,
            DATE_WRITER.format(this.atTime)
        );
    }

    @Override
    public String toString() {
        // Adds the type of the Task and its time period to the base toString() representation
        return String.format("[E]%s (at: %s)", super.toString(), DATE_FORMATTER.format(this.atTime));
    }
}
