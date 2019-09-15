package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    private String by;
    private LocalDateTime time;

    /**
     * Constructs a deadline Task.
     *
     * @param description description of the task
     * @param by deadline of the task
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            time = LocalDateTime.parse(by, formatter);
        } catch (IllegalArgumentException | DateTimeParseException e) {
            throw new DukeException(e.getMessage());
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }

    /**
     * Converts the task into a StringBuilder object to be written into storage.
     *
     * @return StringBuilder object for file writing.
     */
    @Override
    public StringBuilder saveData() {
        return new StringBuilder("D|").append(super.saveData()).append("|").append(by);
    }

    /**
     * Returns a string representation of this task.
     *
     * @return a string representation of this task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
