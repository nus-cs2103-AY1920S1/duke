import java.util.Date;

public class Event extends Task {
    protected Date eventDate;

    public Event(String description, String eventDateString) throws DukeException {
        super(description);
        this.eventDate = DateManager.stringToDate(eventDateString);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateManager.dateToString(eventDate) + ")";
    }

    @Override
    public String getSaveString() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | "
                + DateManager.dateToString(eventDate);
    }
}
