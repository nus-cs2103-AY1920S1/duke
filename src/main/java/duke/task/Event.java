package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");

    /**
     * Constructor for the Event object.
     *
     * @param description The description of the task
     * @param at When the Event will take place
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, formatter);
    }

    /**
     * Prepares the task to be saved to flat file format.
     */
    @Override
    public String printSave() {
        return "E | " + ((isDone) ? "1" : "0") + " | " + description + " | " + at.format(formatter);
    }

    /**
     * Prepares the task to be output on the GUI.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }
}