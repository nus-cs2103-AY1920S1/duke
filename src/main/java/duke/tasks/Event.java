package duke.tasks;
public class Event extends Task {
    private String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }

    @Override
    public String format() {
        String formatted = "E | ";
        int binary = 0;
        if (super.isDone == true) {
            binary = 1;
        }
        formatted += binary + " | " + super.description + " | " + this.eventTime;
        return formatted;
    }
}
