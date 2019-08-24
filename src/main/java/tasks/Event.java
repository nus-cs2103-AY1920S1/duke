package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("d MMMM yyyy, ha")) + ")";
}

    @Override
    public String fileString() {
        return "E" + super.fileString() + " | " + at.format(DateTimeFormatter.ofPattern("d MMMM yyyy, ha"));
    }

}