package task;

import task.DateTime;

/**
 * Represents a Task with type Event
 */
public class Event extends Task {
    private DateTime dateTime;

    public Event(String name, DateTime dateTime){
        super(name);
        this.dateTime = dateTime;
    }

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
