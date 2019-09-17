package duke.task;

/**
 * Class that represents an event.
 */
public class Event extends Task {

    /**
     * Constructor that takes in description and date and time of the event.
     * @param description The main message of the event.
     * @param extraInfo The date and time of the event in exact format.
     */
    public Event(String description, String extraInfo) {
        super(description);
        this.extraInfo = extraInfo;
        this.type = "event";
    }

    @Override
    public String toString() {
        return "[ E ]" + super.toString() + " (at: " + this.extraInfo + ")";
    }
}
