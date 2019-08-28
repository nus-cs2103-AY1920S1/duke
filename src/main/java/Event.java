/**
 * Represents a Task with type Event
 */
public class Event extends Task {
    private DateTime dateTime;

    Event(String name, DateTime dateTime){
        super(name);
        this.dateTime = dateTime;
    }

    Event(String name, boolean isComplete, DateTime dateTime) {
        super(name, isComplete);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateTime + ")";
    }

    @Override
    String publishTask() {
        return "E | " + super.publishTask() + " | " + this.dateTime;
    }
}
