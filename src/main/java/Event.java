import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {

    protected LocalTime startTime;
    protected LocalTime endTime;
    protected LocalDate date;
    protected String text;
    protected String at;

    public Event(String description, DateTimeRangeHelper helper, String at) {
        super(description);
        this.startTime = helper.getStartTime();
        this.endTime = helper.getEndTime();
        this.date = helper.getDate();
        this.text = helper.getText();
        this.at = at;
    }

    public Event(String description) throws DukeException {
        super(description);
        String[] descArr = description.split(" /at ", 2);
        this.description = descArr[0];
        DateTimeRangeHelper helper = DateTimeHandler.getDateTimeRange(descArr[1]);
        this.startTime = helper.getStartTime();
        this.endTime = helper.getEndTime();
        this.date = helper.getDate();
        this.text = helper.getText();
        this.at = descArr[1];

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