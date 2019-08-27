package duke.task;

/**
 * Encapsulates a task object of type Event.
 */
public class Event extends Task {
    private String date;

    /**
     * Construct an Event object.
     *
     * @param topic the topic of the event.
     * @param date the date of the event.
     */
    public Event(String topic, String date) {
        super(topic);
        this.date = date;
        this.type = "E";
        this.details = String.format("%s (at: %s)", topic, date);
        this.detailsForDatabase = String.format("%s | %s", topic, date);
    }
}
