import java.util.Date;
import java.text.SimpleDateFormat;

public class EventTask extends Task {
    public static final SimpleDateFormat DATE_WRITER = new SimpleDateFormat("dd/MM/yyyy HHmm");
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy, h:mm a");

    protected Date atTime;

    public EventTask(String description, Date atTime) {
        super(description);
        this.atTime = atTime;
    }

    public Date getTime() {
        return this.atTime;
    }

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
