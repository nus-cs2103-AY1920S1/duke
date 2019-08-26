/**
 * Represents a Task with type Event
 */
public class Event extends Task {
    private DateTime dateTime;

    Event(String name, String dateTime) throws DukeException {
        super(name);
        //String[] arr = dateTime.trim().split(" ", 2);
        this.dateTime = new DateTime(dateTime.trim());
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
