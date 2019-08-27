import java.util.Date;
import java.text.SimpleDateFormat;

public class DeadlineTask extends Task {
    public static final SimpleDateFormat DATE_WRITER = new SimpleDateFormat("dd/MM/yyyy HHmm");
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy, h:mm a");

    protected Date byDeadline;

    public DeadlineTask(String description, Date byDeadline) {
        super(description);
        this.byDeadline = byDeadline;
    }

    public Date getDeadline() {
        return this.byDeadline;
    }

    public String toEncodedString() {
        return String.format(
            "D | %d | %s | %s",
            this.isComplete ? 1 : 0,
            this.description,
            DATE_WRITER.format(this.byDeadline)
        );
    }

    @Override
    public String toString() {
        // Adds the type of the Task and its deadline to the base toString() representation
        return String.format("[D]%s (by: %s)", super.toString(), DATE_FORMATTER.format(this.byDeadline));
    }
}
