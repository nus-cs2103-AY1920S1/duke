package tasks;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    LocalDateTime date1;

    /**
     * This is a constructor for tasks.Deadline.
     * @param description description of task
     * @param by due date of deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        super.symbol = "D";
    }
    /**
     * This method is used to return the task list.
     *
     * @return date
     */
    public LocalDateTime getDate() throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        date1 = date1.parse(by,formatter);
        return date1;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}