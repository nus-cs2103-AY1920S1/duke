import java.util.Date;
import java.text.SimpleDateFormat;

public class Deadline extends Task {
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy, h:mm a");

    protected Date byDeadline;

    public Deadline(String description, Date byDeadline) {
        super(description);
        this.byDeadline = byDeadline;
    }

    public Date getDeadline() {
        return this.byDeadline;
    }

    @Override
    public String toString() {
        // Adds the type of the Task and its deadline to the base toString() representation
        return String.format("[D]%s (by: %s)", super.toString(), DATE_FORMATTER.format(this.byDeadline));
    }
}
