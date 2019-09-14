package bot.duke.task;

import java.util.Date;

public class Event extends Task {

    /** Starting Time(with date) of event. */
    private Date eventTimeFrom;
    /** Starting Time(with date) of event. */
    private Date eventTimeTo;


    /**
     * Constructs the Event object.
     *
     * @param eventName     Name of event
     * @param eventTimeFrom Starting Date and Time of event
     * @param eventTimeTo   Ending Date and Time of event
     */
    public Event(String eventName, Date eventTimeFrom, Date eventTimeTo) {
        super(eventName);
        this.eventTimeFrom = eventTimeFrom;
        this.eventTimeTo = eventTimeTo;
    }

    /**
     * Returns the Event name.
     *
     * @return Event name
     */
    @Override
    public String getTaskName() {
        return super.getTaskName()
                + " (at: " + Task.DATE_FORMAT.format(eventTimeFrom) + " - "
                + Task.DATE_FORMAT.format(eventTimeTo) + ")";
    }

    /**
     * Returns the Representing Letter to distinguish the Task types.
     *
     * @return Representing Letter
     */
    @Override
    public char getRepLetter() {
        return 'E';
    }

    /**
     * Returns a bar delimited string for storage-related purposes.
     *
     * @return Bar delimited string
     */
    @Override
    public String toDelimitedString() {
        return String.format("%c | %c | %s | %s | %s",
                this.getRepLetter(), this.isDone() ? 'T' : 'F', super.getTaskName(),
                Task.DATE_FORMAT.format(this.eventTimeFrom), Task.DATE_FORMAT.format(this.eventTimeTo));
    }

}
