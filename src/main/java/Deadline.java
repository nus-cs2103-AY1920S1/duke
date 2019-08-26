import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Encapsulates a Task object of the type Deadline.
 * Represents a Deadline task that has specific due date/ due time.
 */
public class Deadline extends Task {
    protected Date endDate;

    /**
     * Constructs a new Deadline task.
     *
     * @param description This is the short description of the task.
     * @param endDate This is specifies when the Deadline Task expires.
     *                It should include the due date or due time or both.
     *                The format should follow "by: Day Time".
     *                E.g. by: Sunday 5pm / by: 11/10/2019 3pm
     *                Alternatively, the user has
     *                the freedom to specify their own duration of
     *                the event such as "by no idea :-P"
     */
    public Deadline(String description, Date endDate) {
        super(description);
        this.endDate = endDate;
        this.typeOfTask = "D";
    }

    public String getDueInString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        return format.format(endDate);
    }

    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        return ("[" + typeOfTask + "]" + "[" + statusIcon + "] "
                + description + "(by: "
                + format.format(endDate) + ")");
    }
}
