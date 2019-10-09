package duke.task;

import duke.util.DateParser;

import java.time.LocalDateTime;

/**
 * Events are Tasks that have a specific starting time.
 */
public class Event extends Task {

    /** Starting time of the event. */
    private LocalDateTime time;

    /**
     * Creates a new Event with the given description and start time.
     *
     * @param description Description of Event.
     * @param time Starting time of the Event.
     */
    public Event(String description, String time) {
        super(description);
        this.time = DateParser.parse(time);
    }

    /**
     * Creates a new Event with the given description, start time and status.
     *
     * @param description Description of Event.
     * @param time Start time of the Event.
     * @param isDone Whether the Event is done or not.
     * @param priority Level of priority for the Event.
     */
    public Event(String description, String time, boolean isDone,
                 Priority priority) {
        super(description, isDone, priority);
        this.time = DateParser.parse(time);
    }

    /**
     * Returns the letter "E", representing the type Event.
     *
     * @return The letter "E".
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Returns a representation of the current Event, including its time, in
     * an appropriate format for data storage. The time is formatted according
     * to the default DateTimeFormatter given by DateParser.
     *
     * @return String representing the current Event.
     */
    @Override
    public String formatAsData() {
        return super.formatAsData() + " | "
                + time.format(DateParser.getDefaultFormat());
    }

    /**
     * Returns a string containing the type of Task, done status, description,
     * and time. The time is formatted according to the default
     * DateTimeFormatter given by DateParser.
     *
     * @return String describing the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + time.format(DateParser.getDefaultFormat()) + ")";
    }
}
