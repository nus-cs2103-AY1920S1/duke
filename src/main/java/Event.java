/**
 * Encapsulates an Task object of the type Event.
 * Represents a Event task that has a specific duration.
 */
public class Event extends Task {
    protected String duration;

    /**
     * Constructs an Event object.
     *
     * @param description This is the short description of the task.
     * @param duration This represents the duration of the Event will last.
     *                 It should include the day the event is held
     *                 and the time frame of the event.
     *                 The format should follow "at: Day Time period".
     *                 E.g. at: Sunday 2 - 4pm
     *                 Alternatively, the user has
     *                 the freedom to specify their own duration of
     *                 the event such as "by no idea :-P"
     */
    public Event(String description, String duration) {
        super(description);
        this.typeOfTask = "E";
        this.duration = duration;
    }

    @Override
    public String toString() {
        String[] prepositionSplit = duration.split(" ",2);
        String statusIcon = this.getStatusIcon();
        return ("[" + typeOfTask + "]" + "[" + statusIcon + "] "
                + description + "(" + prepositionSplit[0] + ": "
                + prepositionSplit[1] + ")");
    }
}
