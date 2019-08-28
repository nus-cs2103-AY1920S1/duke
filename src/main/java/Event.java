import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {

    protected LocalTime startTime;
    protected LocalTime endTime;
    protected LocalDate date;
    protected String text;
    protected String at;

    public Event(String description, DateTimeRangeHelper helper, String at) throws EventException {
        super(description);
        try {
            this.startTime = helper.getStartTime();
            this.endTime = helper.getEndTime();
            this.date = helper.getDate();
            this.text = helper.getText();
            this.at = at;
        } catch (Exception ex) {

        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + text + ")";
    }

    @Override
    public String toStringFile() {
        return "E | " + ((isDone) ? "1" : "0") + " | " + description + " | " + text + " | " + at;
    }
}