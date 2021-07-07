package duke.task;

import duke.Datetime;

/**
 * Represents an Event in the task list.
 */
public class Event extends Task {
    private Datetime datetime;

    public Event(String text, Datetime datetime) {
        super(text);
        this.datetime = datetime;
    }

    public Event(String text, boolean done, Datetime datetime) {
        super(text, done);
        this.datetime = datetime;
    }

    /**
     * Returns the Datetime associated with the Event.
     * @return the Datetime associated with the Event.
     */
    @Override
    public Datetime getDatetime() {
        return this.datetime;
    }

    /**
     * Returns the string representation of the Event type.
     * @return the string representation of the Event type.
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Returns the string representation of am Event.
     * @return the string representation of am Event.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", this.getIcon(), this.getText(), this.datetime);
    }
}
