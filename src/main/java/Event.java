/**
 * This class represents Event tasks which is a type of Task.
 */
public class Event extends Task {
    String at;
    public Event(String description, String at) {
        super(description);
        this.typeOfTask = "[E]";
        this.at = at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}
