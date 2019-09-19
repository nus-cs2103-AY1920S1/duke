package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");

    /**
     * Constructor for the Event object.
     *
     * @param description The description of the task
     * @param by When the Deadline must take place by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, formatter);
    }

    /**
     * Prepares the task to be saved to flat file format.
     */
    @Override
    public String printSave() {
        return "D | " + ((isDone) ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }

    /**
     * Prepares the task to be output on the GUI.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}