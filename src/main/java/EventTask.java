import java.util.Calendar;

/**
 * Object representing an Event task.
 */
public class EventTask extends TimedTask {

    public EventTask(String details, Calendar date) {
        super(details, date);
    }

    @Override
    protected String toFileString() {
        int done = isDone ? 1 : 0;
        return "E" + " | " + done + " | " + details + " | " + fileDateFormat.format(date.getTime());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateFormat.format(date.getTime()) + ")";
    }
}