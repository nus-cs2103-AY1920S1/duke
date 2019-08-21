/*
 * Event.java
 * Level-5
 * CS2103T
 * @author Gabriel Ong
 *
 * This class represents a Event Task.
 *
 */

public class Event extends Task {
    protected String at;

    public Event(String description) {
        super(description);
        String[] splitDescription = description.split(" /at ", 2);
        this.description = splitDescription[0];
        this.at = splitDescription[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
