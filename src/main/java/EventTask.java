import java.util.Calendar;

public class EventTask extends TimedTask {

    public EventTask(String details, Calendar date) {
        super(details, date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateFormat.format(date.getTime()) + ")";
    }
}