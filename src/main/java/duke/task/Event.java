package duke.task;

import duke.command.Command;

import java.util.Date;

public class Event extends Task {

    private Date eventTimeFrom;
    private Date eventTimeTo;


    /**
     * Constructs the Event object.
     * @param eventName Name of event
     * @param eventTimeFrom Starting Date and Time of event
     * @param eventTimeTo Ending Date and Time of event
     */
    public Event(String eventName, Date eventTimeFrom, Date eventTimeTo) {
        super(eventName);
        this.eventTimeFrom = eventTimeFrom;
        this.eventTimeTo = eventTimeTo;
    }

    @Override
    public String getTaskName() {
        return super.getTaskName()
                + " (at: " + Command.DATE_FORMAT.format(eventTimeFrom) + " - "
                + Command.DATE_FORMAT.format(eventTimeTo) + ")";
    }

    @Override
    public char getRepLetter() {
        return 'E';
    }

    @Override
    public String toDelimitedString() {
        return String.format("%c | %c | %s | %s | %s",
                this.getRepLetter(), this.isDone() ? 'T' : 'F', super.getTaskName(),
                Command.DATE_FORMAT.format(this.eventTimeFrom), Command.DATE_FORMAT.format(this.eventTimeTo));
    }

}
