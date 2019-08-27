import java.util.Calendar;

public class DeadlineTask extends TimedTask {

    public DeadlineTask(String details, Calendar deadline) {
        super(details, deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateFormat.format(date.getTime()) + ")";
    }
}
