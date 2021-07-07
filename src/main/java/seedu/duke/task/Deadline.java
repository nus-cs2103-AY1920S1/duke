package seedu.duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Creates a deadline object.
     * 
     * @param done        a boolean denoting the status of the task
     * @param description a string describing the deadline
     * @param by          a string following "dd/mm/yyyy hh:mm" denoting the deadline
     * @throws DateTimeParseException if the format is not as above
     */
    public Deadline(boolean done, String description, String by) throws DateTimeParseException {
        this(done, description, parseTime(by));
    }

    /**
     * Creates a deadline object.
     * 
     * @param done        a boolean denoting the status of the task
     * @param description a string describing the deadline
     * @param by          a string following "dd/mm/yyyy hh:mm" denoting the deadline
     */
    public Deadline(boolean done, String description, LocalDateTime by) {
        super(description);
        this.by = by;
        this.isDone = done;
    }

    /**
     * Creates a deadline object.
     * 
     * @param description a string describing the deadline
     * @param by          a string following "dd/mm/yyyy hh:mm" denoting the deadline
     * @throws DateTimeParseException if the format is not as above
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        this(false, description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String saveFormat() {
        return String.format(" D | %d | %s | %s\n", isDone ? 1 : 0, getDesc(), by);
    }

    /**
     * Takes in an array of string, consist of space-split strings from a saved input. Returns a
     * Deadline object.
     * 
     * @param tokens an array of strings
     * @return an {@link Optional} {@link Deadline}.
     *
     * @throws NumberFormatException  if the number representing done is not 1 or 0
     * @throws DateTimeParseException if the date format is illegal
     */
    public static Deadline fromFormattedString(String[] tokens)
            throws NumberFormatException, DateTimeParseException {
        boolean done = Integer.parseInt(tokens[1]) == 1;
        LocalDateTime by = LocalDateTime.parse(tokens[3]);
        Deadline deadline = new Deadline(done, tokens[2], by);
        return deadline;
    }
}
