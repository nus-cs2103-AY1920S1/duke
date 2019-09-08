package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");

    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, formatter);
    }

    @Override
    public String printSave() {
        return "E | " + ((isDone) ? "1" : "0") + " | " + description + " | " + at.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }
}