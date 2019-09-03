package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {

    private String at;
    private LocalDateTime time;

    /**
     * Constructs an event Task.
     * @param description description of the event
     * @param at time and date of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            time = LocalDateTime.parse(at, formatter);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }

    /**
     * Converts the task into a StringBuilder object to write into storage.
     * @return a StringBuilder object for file writing
     */
    @Override
    public StringBuilder saveData() {
        return new StringBuilder("E|").append(super.saveData()).append("|").append(at);
    }

    /**
     * Returns a string representation of this task.
     * @return a string representation of this task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}