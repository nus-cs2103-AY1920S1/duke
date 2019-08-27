/*
 * Event.java
 * CS2103T
 * @author Gabriel Ong
 *
 * This class represents a Event Task.
 *
 */

import java.io.Serializable;

public class Event extends Task implements Serializable {
    protected String at;

    public Event(String description) throws DukeException {
        super(description);
        String[] splitDescription = description.split(" /at ", 2);
        try {
            this.description = splitDescription[0];
            this.at = splitDescription[1];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a deadline using \"/at\".");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
