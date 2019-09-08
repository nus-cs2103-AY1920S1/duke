package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, formatter);
    }

    @Override
    public String printSave() {
        return "D | " + ((isDone) ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}