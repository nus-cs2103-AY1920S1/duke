package task;

import task.DateTime;

/**
 * Represents a Task with type Event
 */
public class Event extends Task {
    private DateTime dateTime;

    /**
     * Creates a new Event task
     * @param name      The name of the event
     * @param dateTime  The date and time of the event, in DateTime format
     */
    public Event(String name, DateTime dateTime){
        super(name);
        this.dateTime = dateTime;
    }

    /**
     * Creates a new Event task
     * @param name      The name of the event
     * @param isComplete    The completion status of the event
     * @param dateTime  The date and time of the event, in DateTime format
     */
    public Event(String name, boolean isComplete, DateTime dateTime) {
        super(name, isComplete);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateTime + ")";
    }

    @Override
    public String publishTask() {
        return "E | " + super.publishTask() + " | " + this.dateTime;
    }
}
