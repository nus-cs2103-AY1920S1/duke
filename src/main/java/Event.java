/**
 * Represents a Task with type Event
 */
public class Event extends Task {
    private String dateTime;

    Event(String name, String dateTime) {
        super(name);
        String[] arr = dateTime.split(" ", 2);
        this.dateTime = arr[1];
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
