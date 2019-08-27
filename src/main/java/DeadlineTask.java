import java.util.Calendar;

/**
 * Object representing a Deadline Task.
 */
public class DeadlineTask extends TimedTask {

    public DeadlineTask(String details, Calendar deadline) {
        super(details, deadline);
    }

    @Override
    protected String toFileString() {
        int done = isDone ? 1 : 0;
        return "D" + " | " + done + " | " + details + " | " + dateFormat.format(date.getTime());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateFormat.format(date.getTime()) + ")";
    }
}
