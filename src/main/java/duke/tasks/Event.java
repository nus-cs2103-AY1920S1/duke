/**
 * This child class of Task contains the same attributes as its parent class but with an additional field (the timing of
 * that event, which is stored as 'eventTime').
 */
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
